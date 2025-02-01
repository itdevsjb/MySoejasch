package com.tpsmedia.mysoejasch.warehouse.stokbarang


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
import com.tpsmedia.mysoejasch.api.Interface
import com.tpsmedia.mysoejasch.api.Sinkronasi
import com.tpsmedia.mysoejasch.databinding.ActivityProduksiBinding
import com.tpsmedia.mysoejasch.databinding.ActivityStokBarangBinding
import com.tpsmedia.mysoejasch.model.Gudang.Gudangrequest
import com.tpsmedia.mysoejasch.model.Lokasi.Lokasirequest

import com.tpsmedia.mysoejasch.model.Warehouse.Produksi.Produksi
import com.tpsmedia.mysoejasch.model.Warehouse.StokBarang.StokBarang
import com.tpsmedia.mysoejasch.service.SQLiteHelper
import com.tpsmedia.mysoejasch.service.ServiceData
import com.tpsmedia.mysoejasch.service.ServiceLogin
import com.tpsmedia.mysoejasch.warehouse.WarehouseActivity
import com.tpsmedia.mysoejasch.warehouse.kartustok.KartuStockActivity
import com.tpsmedia.mysoejasch.warehouse.location.LocationActivity
import com.tpsmedia.mysoejasch.warehouse.pallet.PalletActivity
import com.tpsmedia.mysoejasch.warehouse.produksi.ProduksiActivity
import com.tpsmedia.mysoejasch.warehouse.stokkeluar.StokKeluarActivity
import com.tpsmedia.mysoejasch.warehouse.stokmasuk.StokMasukActivity
import com.tpsmedia.mysoejasch.warehouse.stokmutasi.StokMutasiActivity
import com.tpsmedia.soejaschapp.Warehouse.BarangHolder
import com.tpsmedia.soejaschapp.Warehouse.WProduksiHolder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class StokBarangActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStokBarangBinding
    private lateinit var dbHelper: SQLiteHelper

    private var drawerLayout: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStokBarangBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbHelper = SQLiteHelper(this)

        val sgSharedPref =
            applicationContext.getSharedPreferences("data_mysoejasch_form", MODE_PRIVATE)
        val editor = sgSharedPref.edit()
        editor.putString("select_date", getFormattedDate())
        editor.putString("select_type", "bn")
        editor.apply()

        initToolbar()
        initComponent()
        getData()

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
        supportActionBar!!.title = "Stok Barang "
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
    private val handler = Handler(Looper.getMainLooper())
    private val debounceRunnable = Runnable {
        getData()
    }

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
                handler.removeCallbacks(debounceRunnable)
                handler.postDelayed(debounceRunnable, 1000)

                return true
            }
        })
        return true
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings) {
            cekHPS()
            //setupDatePicker()
        } else {
            Toast.makeText(applicationContext, item.title, Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun cekHPS() {

        val serviceLogin = ServiceLogin(this)
        val service2 = Client.getClient(this).create(
            Interface::class.java
        )
        val call = service2.postGudangArr("Bearer "+ serviceLogin.token,  serviceLogin.loginId)
        call.enqueue(object : Callback<Gudangrequest> {
            override fun onResponse(call: Call<Gudangrequest>, response: Response<Gudangrequest>) {
                if (response.isSuccessful) {
                    val choices = response.body()?.choices?.toTypedArray()

                    if (choices != null && choices.isNotEmpty()) {
                        val builder = AlertDialog.Builder(this@StokBarangActivity)
                        builder.setTitle("Pilih salah satu")
                        builder.setItems(choices) { _, which ->
                            val sgSharedPref = applicationContext.getSharedPreferences("data_mysoejasch_form", Context.MODE_PRIVATE)
                            val editor = sgSharedPref.edit()
                            editor.putString("factory_pilihan", choices[which])
                            editor.apply()

                            cekHPSLokasi(choices[which])

                            // Menampilkan pesan
                            // Toast.makeText(this@StokMasukActivity, "Pilihan: ${choices[which]}", Toast.LENGTH_SHORT).show()
                        }
                        builder.show()
                    } else {
                        Toast.makeText(this@StokBarangActivity, "Tidak ada pilihan tersedia.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e("API Response", "Response failed with status: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<Gudangrequest>, t: Throwable) {
                Log.e("API Error", "Request failed: ${t.message}")
            }
        })

    }


    private fun cekHPSLokasi(nama_gdg: String) {

        val serviceLogin = ServiceLogin(this)
        val serviceData = ServiceData(this)
        val service2 = Client.getClient(this).create(
            Interface::class.java
        )

        val call = service2.postLokasiArr2("Bearer "+ serviceLogin.token,  serviceLogin.loginId, nama_gdg)
        call.enqueue(object : Callback<Lokasirequest> {
            override fun onResponse(call: Call<Lokasirequest>, response: Response<Lokasirequest>) {
                if (response.isSuccessful) {
                    val choices = response.body()?.choices?.toTypedArray()

                    if (choices != null && choices.isNotEmpty()) {
                        val builder = AlertDialog.Builder(this@StokBarangActivity)
                        builder.setTitle("Pilih salah satu")
                        builder.setItems(choices) { _, which ->
                            val sgSharedPref = applicationContext.getSharedPreferences("data_mysoejasch_form", Context.MODE_PRIVATE)
                            val editor = sgSharedPref.edit()
                            editor.putString("lokasi_pilihan", choices[which])
                            editor.apply()

                            getData()
                        }
                        builder.show()
                    } else {
                        Toast.makeText(this@StokBarangActivity, "Tidak ada pilihan tersedia.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e("API Response", "Response failed with status: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<Lokasirequest>, t: Throwable) {
                Log.e("API Error", "Request failed: ${t.message}")
            }
        })

    }

//    private fun setupDatePicker() {
//        val constraintsBuilder = CalendarConstraints.Builder()
//            .setValidator(DateValidatorPointBackward.now())
//            .build()
//
//        val datePicker = MaterialDatePicker.Builder.datePicker()
//            .setCalendarConstraints(constraintsBuilder)
//            .build()
//
//        datePicker.addOnPositiveButtonClickListener { selection ->
//            val selectedDate = selection?.let { formatDate(it) }
//
//            val sgSharedPref = applicationContext.getSharedPreferences("data_mysoejasch_form", MODE_PRIVATE)
//            val editor = sgSharedPref.edit()
//            editor.putString("selected_date", selectedDate)
//            editor.apply()
//
//            getData()
//        }
//
//        datePicker.show(supportFragmentManager, datePicker.toString())
//    }

    private fun formatDate(timestamp: Long): String {
        val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
        return dateFormat.format(java.util.Date(timestamp))
    }

    fun getFormattedDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // You can customize the pattern
        val date = Date()
        return dateFormat.format(date)
    }

    fun getFirstDateOfMonth(): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val firstDayOfMonth = calendar.time
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(firstDayOfMonth)
    }

    private fun initComponent() {
//        getData()
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
        val call = service.getStok("Bearer " + serviceLogin.token, "bn", serviceData.lokasi_pilihan, searchKeyword)
        call.enqueue(object : Callback<StokBarang> {
            override fun onResponse(
                call: Call<StokBarang>,
                response: Response<StokBarang>
            ) {

                val responseData = response.body()?.data
                if (responseData != null) {
                    val mAdapter = BarangHolder(responseData)
                    val recyclerView = binding.recyclerView
                    recyclerView.layoutManager = LinearLayoutManager(this@StokBarangActivity)
                    recyclerView.adapter = mAdapter
                    recyclerView.itemAnimator = DefaultItemAnimator()
                    println("Ada data")
                }

            }

            override fun onFailure(call: Call<StokBarang>, t: Throwable) {
            }
        })

        binding.swipeRefresh.isRefreshing = false
    }


    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(applicationContext, WarehouseActivity::class.java))
    }

}