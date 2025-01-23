package com.tpsmedia.mysoejasch.warehouse

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.tpsmedia.materialx.ui.design.utils.Tools
import com.tpsmedia.mysoejasch.LoginActivity
import com.tpsmedia.mysoejasch.MainActivity
import com.tpsmedia.mysoejasch.R
import com.tpsmedia.mysoejasch.databinding.ActivityWarehouseBinding
import com.tpsmedia.mysoejasch.service.BluetoothPrinterHelper
import com.tpsmedia.mysoejasch.service.SQLiteHelper
import com.tpsmedia.mysoejasch.service.ServiceLogin
import com.tpsmedia.mysoejasch.warehouse.kartustok.KartuStockActivity
import com.tpsmedia.mysoejasch.warehouse.location.LocationActivity
import com.tpsmedia.mysoejasch.warehouse.pallet.PalletActivity
import com.tpsmedia.mysoejasch.warehouse.produksi.ProduksiActivity
import com.tpsmedia.mysoejasch.warehouse.stokbarang.StokBarangActivity
import com.tpsmedia.mysoejasch.warehouse.stokkeluar.StokKeluarActivity
import com.tpsmedia.mysoejasch.warehouse.stokmasuk.StokMasukActivity
import com.tpsmedia.mysoejasch.warehouse.stokmutasi.StokMutasiActivity
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class WarehouseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWarehouseBinding
    val groupAdapter = GroupAdapter<GroupieViewHolder>()

    private lateinit var dbHelper: SQLiteHelper
    private lateinit var bluetoothPrinterHelper: BluetoothPrinterHelper

    private var drawerLayout: DrawerLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWarehouseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbHelper = SQLiteHelper(this)
        bluetoothPrinterHelper = BluetoothPrinterHelper(this)

        initToolbar()

        drawerLayout = binding.drawerLayout
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.nav_item_1 -> {}
                R.id.nav_item_2 -> {}
                R.id.nav_bluetoot -> {
                    bluetoothPrinterHelper.selectBluetoothDevice { isConnected ->
                        if (isConnected) {
                            Toast.makeText(this, "Perangkat siap untuk mencetak", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                R.id.nav_logout -> logout()
            }
            drawerLayout!!.closeDrawers()
            true
        }


        val sgSharedPref =
            applicationContext.getSharedPreferences("data_mysoejasch_form", MODE_PRIVATE)
        val editor = sgSharedPref.edit()
        editor.putString("start_date", getFirstDateOfMonth())
        editor.putString("end_date", getFormattedDate())
        editor.apply()


    }

    fun getFirstDateOfMonth(): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val firstDayOfMonth = calendar.time
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(firstDayOfMonth)
    }

    fun getFormattedDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // You can customize the pattern
        val date = Date()
        return dateFormat.format(date)
    }

    private fun initToolbar() {
        val serviceLogin = ServiceLogin(this)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = "Warehouse"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationIcon(R.drawable.ic_menu)
        binding.toolbar.setNavigationOnClickListener { v ->
            if (drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
                drawerLayout!!.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout!!.openDrawer(GravityCompat.START)
            }
        }
        Tools.setSystemBarColor(this)
    }

    fun showOnProcces(view: View?) {
        Toast.makeText(applicationContext, "Menu dalam pengembangan", Toast.LENGTH_SHORT).show()
    }

    fun showOnPallet(view: View?) {
        startActivity(Intent(applicationContext, PalletActivity::class.java))
    }

    fun showOnLocation(view: View?) {
        startActivity(Intent(applicationContext, LocationActivity::class.java))
    }



    fun showOnProduksi(view: View?) {
        startActivity(Intent(applicationContext, ProduksiActivity::class.java))
    }

    fun showOnStokMasuk(view: View?) {
        startActivity(Intent(applicationContext, StokMasukActivity::class.java))
    }

    fun showOnStokKeluar(view: View?) {
        startActivity(Intent(applicationContext, StokKeluarActivity::class.java))
    }

    fun showOnStokMutasi(view: View?) {
//        startActivity(Intent(applicationContext, StokMutasiActivity::class.java))
        Toast.makeText(this, "Akan dirilis setelah stok lama habis", Toast.LENGTH_SHORT).show()
    }



    fun showOnKartuStok(view: View?) {
        startActivity(Intent(applicationContext, KartuStockActivity::class.java))
    }

    fun showOnStokBarang(view: View?) {
        startActivity(Intent(applicationContext, StokBarangActivity::class.java))
    }

    private fun logout() {
        val sgSharedPref = applicationContext.getSharedPreferences("data_mysoejasch", MODE_PRIVATE)
        val editor = sgSharedPref.edit()
        val downloaddata = sgSharedPref.getString("downloaddata", "0")
        editor.clear()
        editor.putString("downloaddata", downloaddata)
        editor.apply()
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(applicationContext, MainActivity::class.java))
    }





}