package com.tpsmedia.mysoejasch.warehouse.produksi


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.navigation.NavigationView
import com.tpsmedia.materialx.ui.design.utils.Tools
import com.tpsmedia.mysoejasch.R
import com.tpsmedia.mysoejasch.api.Client
import com.tpsmedia.mysoejasch.api.Sinkronasi
import com.tpsmedia.mysoejasch.databinding.ActivityProduksiBinding

import com.tpsmedia.mysoejasch.model.Warehouse.Produksi.Produksi
import com.tpsmedia.mysoejasch.service.SQLiteHelper
import com.tpsmedia.mysoejasch.service.ServiceData
import com.tpsmedia.mysoejasch.service.ServiceLogin
import com.tpsmedia.mysoejasch.warehouse.WarehouseActivity
import com.tpsmedia.mysoejasch.warehouse.kartustok.KartuStockActivity
import com.tpsmedia.mysoejasch.warehouse.location.LocationActivity
import com.tpsmedia.mysoejasch.warehouse.pallet.PalletActivity
import com.tpsmedia.mysoejasch.warehouse.stokbarang.StokBarangActivity
import com.tpsmedia.mysoejasch.warehouse.stokkeluar.StokKeluarActivity
import com.tpsmedia.mysoejasch.warehouse.stokmasuk.StokMasukActivity
import com.tpsmedia.mysoejasch.warehouse.stokmutasi.StokMutasiActivity
import com.tpsmedia.soejaschapp.Warehouse.WProduksiHolder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class ProduksiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProduksiBinding
    private lateinit var dbHelper: SQLiteHelper

    private var drawerLayout: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProduksiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbHelper = SQLiteHelper(this)

        val sgSharedPref =
            applicationContext.getSharedPreferences("data_mysoejasch_form", MODE_PRIVATE)
        val editor = sgSharedPref.edit()
        editor.putString("start_date", getFormattedDate())
        editor.putString("end_date", getFormattedDate())
        editor.apply()

        initToolbar()
        initComponent()

        drawerLayout = binding.drawerLayout
        val navigationView = findViewById<NavigationView>(R.id.nav_view)

        val menu = navigationView.menu
        val navHide = menu.findItem(R.id.nav_plan)
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
        binding.toolbar.setNavigationIcon(com.tpsmedia.mysoejasch.R.drawable.ic_menu)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = "Plan Production"
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

    var searchKeyword = ""
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search_option, menu)
        val item = menu.findItem(R.id.action_search2)
        val sv = item.actionView as SearchView
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                searchKeyword = newText
                getData()
                return true
            }
        })
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings) {
            //showBottomSheetDialog()
            Log.e("Check", "Oke klik")
            setupDateRangePicker()
        } else {
            Toast.makeText(applicationContext, item.title, Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupDateRangePicker() {
        val constraintsBuilder = CalendarConstraints.Builder()
            .setValidator(DateValidatorPointBackward.now())
            .build()

        val dateRangePicker = MaterialDatePicker.Builder.dateRangePicker()
            .setCalendarConstraints(constraintsBuilder)
            .build()

        dateRangePicker.addOnPositiveButtonClickListener { selection ->
            val startDate = selection.first?.let { formatDate(it) }
            val endDate = selection.second?.let { formatDate(it) }

            val sgSharedPref =
                applicationContext.getSharedPreferences("data_mysoejasch_form", MODE_PRIVATE)
            val editor = sgSharedPref.edit()
            editor.putString("start_date", startDate)
            editor.putString("end_date", endDate)
            editor.apply()

            getData()
//            Toast.makeText(
//                this,
//                "Tanggal Mulai: $startDate\nTanggal Selesai: $endDate",
//                Toast.LENGTH_LONG
//            ).show()
        }

        dateRangePicker.show(supportFragmentManager, dateRangePicker.toString())


    }

    private fun formatDate(timestamp: Long): String {
        val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
        return dateFormat.format(java.util.Date(timestamp))
    }

    fun getFormattedDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // You can customize the pattern
        val date = Date()
        return dateFormat.format(date)
    }

    private fun initComponent() {
        getData()
        binding.swipeRefresh.setOnRefreshListener {
            getData()
        }
    }
    private fun getData(){
        val serviceLogin = ServiceLogin(this)
        val serviceData = ServiceData(this)
        val service = Client.getClient(this).create(
            Sinkronasi::class.java
        )
        val call = service.postwmsproduksi("Bearer " + serviceLogin.token, serviceData.start_date, serviceData.end_date, searchKeyword)
        call.enqueue(object : Callback<Produksi> {
            override fun onResponse(
                call: Call<Produksi>,
                response: Response<Produksi>
            ) {

                    val responseData = response.body()?.data
                    if (responseData != null) {
                        val mAdapter = WProduksiHolder(responseData)
                        val recyclerView = binding.recyclerView
                        recyclerView.layoutManager = LinearLayoutManager(this@ProduksiActivity)
                        recyclerView.adapter = mAdapter
                        recyclerView.itemAnimator = DefaultItemAnimator()
                        println("Ada data")
                    }

            }

            override fun onFailure(call: Call<Produksi>, t: Throwable) {
            }
        })

        binding.swipeRefresh.isRefreshing = false
    }


    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(applicationContext, WarehouseActivity::class.java))
    }

}