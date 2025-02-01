package com.tpsmedia.mysoejasch.warehouse.location


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.tpsmedia.materialx.ui.design.utils.Tools
import com.tpsmedia.mysoejasch.R
import com.tpsmedia.mysoejasch.adapter.LocationAdapter
import com.tpsmedia.mysoejasch.api.ClientWMS
import com.tpsmedia.mysoejasch.api.Sinkronasi
import com.tpsmedia.mysoejasch.databinding.ActivityLocationBinding
import com.tpsmedia.mysoejasch.model.Warehouse.Location.Datum
import com.tpsmedia.mysoejasch.model.Warehouse.Location.LocationList
import com.tpsmedia.mysoejasch.service.SQLiteHelper
import com.tpsmedia.mysoejasch.service.ServiceLogin
import com.tpsmedia.mysoejasch.warehouse.WarehouseActivity
import com.tpsmedia.mysoejasch.warehouse.kartustok.KartuStockActivity
import com.tpsmedia.mysoejasch.warehouse.pallet.PalletActivity
import com.tpsmedia.mysoejasch.warehouse.produksi.ProduksiActivity
import com.tpsmedia.mysoejasch.warehouse.stokbarang.StokBarangActivity
import com.tpsmedia.mysoejasch.warehouse.stokkeluar.StokKeluarActivity
import com.tpsmedia.mysoejasch.warehouse.stokmasuk.StokMasukActivity
import com.tpsmedia.mysoejasch.warehouse.stokmutasi.StokMutasiActivity
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LocationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLocationBinding
    val groupAdapter = GroupAdapter<GroupieViewHolder>()
    var post_model_array_list = ArrayList<Datum>()
    private lateinit var dbHelper: SQLiteHelper
    private lateinit var adapter: LocationAdapter
    private lateinit var recyclerView: RecyclerView


    private var currentPage = 1
    private var isLoading = false
    private val limit = 500

    private var drawerLayout: DrawerLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbHelper = SQLiteHelper(this)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = LocationAdapter(mutableListOf())
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!isLoading && isLastItemVisible()) {
//                    loadData()
                    Log.e("TESTINGX", "WAH BACA INI ")
                }
            }
        })

        initToolbar()
        initComponent()
        loadData()


        drawerLayout = binding.drawerLayout
        val navigationView = findViewById<NavigationView>(R.id.nav_view)

        val menu = navigationView.menu
        val navHide = menu.findItem(R.id.nav_location)
        navHide.isVisible = false

        navigationView.setNavigationItemSelectedListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(applicationContext, WarehouseActivity::class.java))
                }
                R.id.nav_plan -> {
                    startActivity(Intent(applicationContext, ProduksiActivity::class.java))
                }
                R.id.nav_location -> {
                    startActivity(Intent(applicationContext, LocationActivity::class.java))
                }
                R.id.nav_pallet -> {
                    startActivity(Intent(applicationContext, PalletActivity::class.java))
                }
                R.id.nav_barang -> {
                    startActivity(Intent(applicationContext, StokBarangActivity::class.java))
                }
                R.id.nav_inbound -> {
                    startActivity(Intent(applicationContext, StokMasukActivity::class.java))
                }
                R.id.nav_outbound -> {
                    startActivity(Intent(applicationContext, StokKeluarActivity::class.java))
                }
                R.id.nav_mutasi -> {
                    startActivity(Intent(applicationContext, StokMutasiActivity::class.java))
                }
                R.id.nav_kartustok -> {
                    startActivity(Intent(applicationContext, KartuStockActivity::class.java))
                }
            }
            drawerLayout!!.closeDrawers()
            true
        }

    }


    private fun initToolbar() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_menu)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = "Data Location"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener { v ->
            if (drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
                drawerLayout!!.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout!!.openDrawer(GravityCompat.START)
            }
        }

        Tools.setSystemBarColor(this)
    }

    private fun initComponent() {
        binding.swipeRefresh.setOnRefreshListener {
            loadData();
        }
    }

    private fun isLastItemVisible(): Boolean {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
        val totalItemCount = layoutManager.itemCount
        return lastVisibleItemPosition >= totalItemCount - 1
    }


    var searchKeyword = ""

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val item = menu.findItem(R.id.action_search)
        val sv = item.actionView as SearchView
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                searchKeyword = newText
                adapter.clearData()
                currentPage = 1
                loadData()
                return true
            }
        })
        return true
    }

    fun addLocation(view: View?) {
        startActivity(Intent(applicationContext, LocationFormActivity::class.java))
    }

    private fun loadData() {
        binding.swipeRefresh.isRefreshing = true

        val serviceLogin = ServiceLogin(this)
        val service2 = ClientWMS.getClientWMS(this).create(
            Sinkronasi::class.java
        )
        val call = service2.getLocation("Bearer "+ serviceLogin.token, currentPage.toString(), limit.toString(), searchKeyword)
        call.enqueue(object : Callback<LocationList> {
            override fun onResponse(call: Call<LocationList>, response: Response<LocationList>) {
                if (response.isSuccessful) {
                    response.body()?.data?.let { data ->
                        adapter.addData(data)
                        Log.e("API Response", "OKE")
                        currentPage++
                    } ?: run {
                        Log.e("API Response", "No data found in response")
                    }
                } else {
                    Log.e("API Response", "Response failed with status: ${response.code()}")
                }
                binding.swipeRefresh.isRefreshing = false
            }
            override fun onFailure(call: Call<LocationList>, t: Throwable) {
                binding.swipeRefresh.isRefreshing = false
                Log.e("API Error", "Request failed: ${t.message}")
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(applicationContext, WarehouseActivity::class.java))
    }


}