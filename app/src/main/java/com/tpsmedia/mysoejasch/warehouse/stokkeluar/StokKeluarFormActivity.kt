package com.tpsmedia.mysoejasch.warehouse.stokkeluar


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.tpsmedia.materialx.ui.design.utils.Tools
import com.tpsmedia.mysoejasch.R
import com.tpsmedia.mysoejasch.api.Client
import com.tpsmedia.mysoejasch.api.ClientWMS
import com.tpsmedia.mysoejasch.api.Interface
import com.tpsmedia.mysoejasch.api.Sinkronasi
import com.tpsmedia.mysoejasch.databinding.ActivityStokkeluarFormBinding
import com.tpsmedia.mysoejasch.model.Gudang.Gudangrequest
import com.tpsmedia.mysoejasch.model.Lokasi.Lokasirequest
import com.tpsmedia.mysoejasch.model.Warehouse.StokKeluar.StokKeluar
import com.tpsmedia.mysoejasch.model.Warehouse.StokKeluar.StokKeluarObject
import com.tpsmedia.mysoejasch.model.Warehouse.StokMasuk.StokMasuk
import com.tpsmedia.mysoejasch.service.SQLiteHelper
import com.tpsmedia.mysoejasch.service.ServiceData
import com.tpsmedia.mysoejasch.service.ServiceLogin
import com.tpsmedia.mysoejasch.warehouse.WarehouseActivity
import com.tpsmedia.mysoejasch.warehouse.kartustok.KartuStockActivity
import com.tpsmedia.mysoejasch.warehouse.location.LocationActivity
import com.tpsmedia.mysoejasch.warehouse.pallet.PalletActivity
import com.tpsmedia.mysoejasch.warehouse.produksi.ProduksiActivity
import com.tpsmedia.mysoejasch.warehouse.stokbarang.StokBarangActivity
import com.tpsmedia.mysoejasch.warehouse.stokkeluar.StokKeluarActivity
import com.tpsmedia.mysoejasch.warehouse.stokmasuk.StokMasukActivity
import com.tpsmedia.mysoejasch.warehouse.stokmutasi.StokMutasiActivity
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StokKeluarFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStokkeluarFormBinding
    private var drawerLayout: DrawerLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStokkeluarFormBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val jenis_keluar = intent.getStringExtra("jenis_keluar");
        if (jenis_keluar.equals("2002")){
            initToolbar("Waste");
        }else{
            initToolbar("Stok Keluar");
        }

        fetchSpinnerData()

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

        binding.btnSubmit.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Konfirmasi")
            builder.setMessage("Apakah Anda yakin ingin melanjutkan?")

            builder.setPositiveButton("Ya") { dialog, which ->
                submitData()
            }
            builder.setNegativeButton("Batal") { dialog, which ->
                Toast.makeText(this, "Aksi dibatalkan", Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }


    }

    private fun initToolbar(title: String) {
        binding.toolbar.setNavigationIcon(R.drawable.ic_menu)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = "Form " + title
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

    private fun fetchSpinnerData() {
        val serviceLogin = ServiceLogin(this)
        val service2 = Client.getClient().create(
            Interface::class.java
        )
        val call = service2.postGudangArr("Bearer "+ serviceLogin.token, serviceLogin.loginId)
        call.enqueue(object : Callback<Gudangrequest> {
            override fun onResponse(call: Call<Gudangrequest>, response: Response<Gudangrequest>) {
                if (response.isSuccessful) {
                    response.body()?.data?.let { spinnerItems ->

                        val adapterku = ArrayAdapter(
                            this@StokKeluarFormActivity,
                            android.R.layout.simple_spinner_item,
                            spinnerItems.map { it.text }
                        )
                        adapterku.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        binding.spinnerGudang.adapter = adapterku
                        binding.spinnerGudang.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    parentView: AdapterView<*>?,
                                    selectedItemView: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    val selectedItem = spinnerItems[position]

                                    val sgSharedPref = applicationContext.getSharedPreferences(
                                        "data_mysoejasch_form",
                                        MODE_PRIVATE
                                    )
                                    val editor = sgSharedPref.edit()
                                    editor.putString("ucode_gdg", selectedItem.value)
                                    editor.apply()

                                    fetchSpinnerDataLokasi(selectedItem.value)
                                }
                                override fun onNothingSelected(parentView: AdapterView<*>?) {
                                }
                            }

                        Log.e("API Response", "OKE")
                    } ?: run {
                        Log.e("API Response", "No data found in response")
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

    private fun fetchSpinnerDataLokasi(ucode_gdg: String) {
        val serviceLogin = ServiceLogin(this)
        val service2 = Client.getClient().create(
            Interface::class.java
        )
        val call = service2.postLokasiArr("Bearer "+ serviceLogin.token, serviceLogin.loginId, ucode_gdg)
        call.enqueue(object : Callback<Lokasirequest> {
            override fun onResponse(call: Call<Lokasirequest>, response: Response<Lokasirequest>) {
                if (response.isSuccessful) {
                    response.body()?.data?.let { spinnerItems ->

                        val adapterku = ArrayAdapter(
                            this@StokKeluarFormActivity,
                            android.R.layout.simple_spinner_item,
                            spinnerItems.map { it.text }
                        )
                        adapterku.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        binding.spinnerUCodeLokasi.adapter = adapterku
                        binding.spinnerUCodeLokasi.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    parentView: AdapterView<*>?,
                                    selectedItemView: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    val selectedItem = spinnerItems[position]

                                    val sgSharedPref = applicationContext.getSharedPreferences(
                                        "data_mysoejasch_form",
                                        MODE_PRIVATE
                                    )
                                    val editor = sgSharedPref.edit()
                                    editor.putString("ucode_lokasi", selectedItem.value)
                                    editor.apply()
                                }
                                override fun onNothingSelected(parentView: AdapterView<*>?) {
                                }
                            }

                        Log.e("API Response", "OKE")
                    } ?: run {
                        Log.e("API Response", "No data found in response")
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

    private fun submitData() {

        val serviceLogin = ServiceLogin(this)
        val serviceData = ServiceData(this)
        val service2 = ClientWMS.getClientWMS().create(
            Sinkronasi::class.java
        )

        val jsonData = """
          { 
            "ucode_st": null,
            "tgl_outbound": "",
            "ucode_jenis_keluar": "",
            "ucode_gdg": "",
            "ucode_lokasi" : "",
            "keterangan": "",
            "created_by": "",
            "checker_by": null,
            "driver_by" : "",
            "no_kendaraan" : "",
            "updated_by": null,
            "factory" : "MMS"
          }
        """

        val jsonObject = JSONObject(jsonData)
        jsonObject.put("ucode_jenis_keluar", intent.getStringExtra("jenis_keluar"))
        jsonObject.put("ucode_gdg", serviceData.ucode_gdg)
        jsonObject.put("ucode_lokasi", serviceData.ucode_lokasi)
        if (serviceData.ucode_gdg.equals("14010000000052")){
            jsonObject.put("factory", "NMT")
        }else{
            jsonObject.put("factory", "MMS")
        }
        jsonObject.put("keterangan", binding.etKeterangan.text)
        jsonObject.put("created_by", serviceLogin.loginId)

        val requestBody = RequestBody.create(
            MediaType.parse("application/json; charset=utf-8"),
            jsonObject.toString()
        )

        val call = service2.postOutbound2("Bearer "+ serviceLogin.token, requestBody)
        call.enqueue(object : Callback<StokKeluarObject> {
            override fun onResponse(call: Call<StokKeluarObject>, response: Response<StokKeluarObject>) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "Success submit data", Toast.LENGTH_SHORT).show();
                    setTimeout(1000) {
                        startActivity(Intent(applicationContext, StokKeluarActivity::class.java))
                    }
                } else {
                    Log.e("API Response", "Response failed with status: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<StokKeluarObject>, t: Throwable) {
                Log.e("API Error Disini", "Request failed: ${t.message}")
            }
        })


    }

    fun setTimeout(delayMillis: Long, action: () -> Unit) {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(action, delayMillis)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(applicationContext, StokKeluarActivity::class.java))
    }


}