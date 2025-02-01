package com.tpsmedia.mysoejasch.warehouse.kartustok


import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.tpsmedia.materialx.ui.design.utils.Tools
import com.tpsmedia.mysoejasch.R
import com.tpsmedia.mysoejasch.api.Client
import com.tpsmedia.mysoejasch.api.Sinkronasi
import com.tpsmedia.mysoejasch.databinding.ActivityKartustockBinding
import com.tpsmedia.mysoejasch.model.Warehouse.KartuStok.KartuStok
import com.tpsmedia.mysoejasch.model.Warehouse.Produksi.Produksi
import com.tpsmedia.mysoejasch.service.SQLiteHelper
import com.tpsmedia.mysoejasch.service.ServiceData
import com.tpsmedia.mysoejasch.service.ServiceLogin
import com.tpsmedia.mysoejasch.warehouse.WarehouseActivity
import com.tpsmedia.mysoejasch.warehouse.location.LocationActivity
import com.tpsmedia.mysoejasch.warehouse.pallet.PalletActivity
import com.tpsmedia.mysoejasch.warehouse.produksi.ProduksiActivity
import com.tpsmedia.mysoejasch.warehouse.stokbarang.StokBarangActivity
import com.tpsmedia.mysoejasch.warehouse.stokkeluar.StokKeluarActivity
import com.tpsmedia.mysoejasch.warehouse.stokmasuk.StokMasukActivity
import com.tpsmedia.mysoejasch.warehouse.stokmutasi.StokMutasiActivity
import com.tpsmedia.soejaschapp.Warehouse.WKartuStokHolder
import com.tpsmedia.soejaschapp.Warehouse.WProduksiHolder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KartuStockActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKartustockBinding
    private var drawerLayout: DrawerLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKartustockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()

        binding.button7.setOnClickListener {
            getData()
        }

        drawerLayout = binding.drawerLayout
        val navigationView = findViewById<NavigationView>(R.id.nav_view)

        val menu = navigationView.menu
        val navHide = menu.findItem(R.id.nav_kartustok)
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
        supportActionBar!!.title = "Kartu Stok"
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


    fun getData(){
        val serviceLogin = ServiceLogin(this)
        val serviceData = ServiceData(this)
        val service = Client.getClient(this).create(
            Sinkronasi::class.java
        )
        val searchKeyword = binding.editTextText.text;
        val call = service.postkartustok("Bearer " + serviceLogin.token, serviceData.start_date, serviceData.end_date, searchKeyword.toString())
        call.enqueue(object : Callback<KartuStok> {
            override fun onResponse(
                call: Call<KartuStok>,
                response: Response<KartuStok>
            ) {

                val responseData = response.body()?.data
                if (responseData != null) {
                    val mAdapter = WKartuStokHolder(responseData)
                    val recyclerView = binding.recyclerView
                    recyclerView.layoutManager = LinearLayoutManager(this@KartuStockActivity)
                    recyclerView.adapter = mAdapter
                    recyclerView.itemAnimator = DefaultItemAnimator()
                    println("Ada data")
                }

            }

            override fun onFailure(call: Call<KartuStok>, t: Throwable) {
            }
        })

        binding.swipeRefresh.isRefreshing = false
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(applicationContext, WarehouseActivity::class.java))
    }


}