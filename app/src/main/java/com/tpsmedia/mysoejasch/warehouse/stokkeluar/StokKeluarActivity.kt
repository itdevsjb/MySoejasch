package com.tpsmedia.mysoejasch.warehouse.stokkeluar
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.InputType
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.marginStart
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.navigation.NavigationView
import com.tpsmedia.materialx.ui.design.utils.Tools
import com.tpsmedia.mysoejasch.R
import com.tpsmedia.mysoejasch.adapter.StokKeluarAdapter
import com.tpsmedia.mysoejasch.api.Client
import com.tpsmedia.mysoejasch.api.ClientWMS
import com.tpsmedia.mysoejasch.api.Interface
import com.tpsmedia.mysoejasch.api.Sinkronasi
import com.tpsmedia.mysoejasch.api.SlugInterface
import com.tpsmedia.mysoejasch.databinding.ActivityStokkeluarBinding
import com.tpsmedia.mysoejasch.model.ApiResponse
import com.tpsmedia.mysoejasch.model.Lokasi.Lokasirequest
import com.tpsmedia.mysoejasch.model.Warehouse.CTPlan
import com.tpsmedia.mysoejasch.model.Warehouse.StokKeluar.StokKeluar
import com.tpsmedia.mysoejasch.model.Warehouse.StokKeluar.StokKeluarObject
import com.tpsmedia.mysoejasch.service.ServiceData
import com.tpsmedia.mysoejasch.service.ServiceLogin
import com.tpsmedia.mysoejasch.warehouse.WarehouseActivity
import com.tpsmedia.mysoejasch.warehouse.kartustok.KartuStockActivity
import com.tpsmedia.mysoejasch.warehouse.location.LocationActivity
import com.tpsmedia.mysoejasch.warehouse.pallet.PalletActivity
import com.tpsmedia.mysoejasch.warehouse.pallet.PalletFormActivity
import com.tpsmedia.mysoejasch.warehouse.produksi.ProduksiActivity
import com.tpsmedia.mysoejasch.warehouse.stokbarang.StokBarangActivity
import com.tpsmedia.mysoejasch.warehouse.stokmasuk.StokMasukActivity
import com.tpsmedia.mysoejasch.warehouse.stokmasuk.StokMasukFormActivity
import com.tpsmedia.mysoejasch.warehouse.stokmutasi.StokMutasiActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StokKeluarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStokkeluarBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StokKeluarAdapter
    private var currentPage = 1
    private var isLoading = false
    private val limit = 20

    val debounceHandler = Handler(Looper.getMainLooper())
    var runnable: Runnable? = null


    private val mBehavior: BottomSheetBehavior<*>? = null
    private var mBottomSheetDialog: BottomSheetDialog? = null
    private val bottom_sheet: View? = null

    private var drawerLayout: DrawerLayout? = null

    var searchKeyword = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStokkeluarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StokKeluarAdapter(mutableListOf())
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!isLoading && isLastItemVisible()) {
                    loadData()
                }
            }
        })

        binding.swipeRefresh.setOnRefreshListener {
            searchKeyword = ""
            currentPage = 1
            adapter.clearData()
        }

        loadData()


        drawerLayout = binding.drawerLayout
        val navigationView = findViewById<NavigationView>(R.id.nav_view)

        val menu = navigationView.menu
        val navHide = menu.findItem(R.id.nav_outbound)
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
        supportActionBar!!.title = "Stock Keluar"
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



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search_option, menu)
        val item = menu.findItem(R.id.action_search2)
        val sv = item.actionView as SearchView
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchKeyword = query
                currentPage = 1
                adapter.clearData()

                //loadData()
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
        return true
    }

    private fun setupDateRangePicker() {
        val constraintsBuilder = CalendarConstraints.Builder()
            .setValidator(DateValidatorPointBackward.now())
            .build()

        val dateRangePicker = MaterialDatePicker.Builder.dateRangePicker()
            .setCalendarConstraints(constraintsBuilder)
            .build()

        // Listener untuk mendapatkan hasil dari date range picker
        dateRangePicker.addOnPositiveButtonClickListener { selection ->
            val startDate = selection.first?.let { formatDate(it) }
            val endDate = selection.second?.let { formatDate(it) }

            val sgSharedPref =
                applicationContext.getSharedPreferences("data_mysoejasch_form", MODE_PRIVATE)
            val editor = sgSharedPref.edit()
            editor.putString("start_date", startDate)
            editor.putString("end_date", endDate)
            editor.apply()

            searchKeyword = ""
            currentPage = 1

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val totalItemCount = layoutManager.itemCount
            if (totalItemCount == 0){
                loadData()
            }else{
                adapter.clearData()
            }
        }

        dateRangePicker.show(supportFragmentManager, dateRangePicker.toString())


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

    private fun loadData() {
        binding.swipeRefresh.isRefreshing = true

        val serviceLogin = ServiceLogin(this)
        val serviceData = ServiceData(this)
        val service2 = ClientWMS.getClientWMS().create(
            Sinkronasi::class.java
        )

        val call = service2.getOutbound("Bearer "+ serviceLogin.token, currentPage.toString(), limit.toString(),serviceData.start_date ,serviceData.end_date, searchKeyword )
        call.enqueue(object : Callback<StokKeluar> {
            override fun onResponse(call: Call<StokKeluar>, response: Response<StokKeluar>) {
                if (response.isSuccessful) {
                    response.body()?.data?.let { data ->
                        adapter.addData(data)
                        currentPage++
                    } ?: run {
                        Log.e("API Response", "No data found in response")
                    }
                } else {
                    Log.e("API Response", "Response failed with status: ${response.code()}")
                }
                binding.swipeRefresh.isRefreshing = false
            }
            override fun onFailure(call: Call<StokKeluar>, t: Throwable) {
                binding.swipeRefresh.isRefreshing = false
                Log.e("API Error", "Request failed: ${t.message}")
            }
        })
    }

    private fun isLastItemVisible(): Boolean {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
        val totalItemCount = layoutManager.itemCount
        return lastVisibleItemPosition >= totalItemCount - 1
    }

    private fun formatDate(timestamp: Long): String {
        val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
        return dateFormat.format(java.util.Date(timestamp))
    }



    //  Bottom Sheet

    private fun showInputDialog() {

        val editText = EditText(this)
        editText.inputType = InputType.TYPE_CLASS_TEXT
        editText.requestFocus()


        editText.setTextColor(Color.BLACK)
        editText.setHintTextColor(Color.GRAY)
        editText.setBackgroundColor(Color.WHITE) // Mengatur warna latar belakang
        editText.setPadding(20, 20, 20, 20) // Mengatur padding untuk EditText
        editText.textSize = 18f // Mengatur ukuran teks
        editText.setMinHeight(150)
        editText.setBackgroundResource(R.drawable.edit_text_round_bg)

        val params = ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params.setMargins(50, 50, 50, 50)
        editText.layoutParams = params

        val dialog = AlertDialog.Builder(this)
            .setTitle("Scan CT Plan")
            .setView(editText)
            .setPositiveButton("OK") { dialog, which ->
                val inputText = editText.text.toString()

                runnable?.let { debounceHandler.removeCallbacks(it) }
                runnable = Runnable {
                    cekCTplan(inputText)
                }
                debounceHandler.postDelayed(runnable!!, 500)

            }
            .setNegativeButton("Batal") { dialog, which ->
                dialog.dismiss()
            }
            .create()
        dialog.show()
    }

    fun showBottomSheetDialog(view: View?) {
        if (mBehavior?.getState()  === BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior?.setState(BottomSheetBehavior.STATE_COLLAPSED)
        }

        val view: View = layoutInflater.inflate(R.layout.sheet_outbound, null)

        mBottomSheetDialog = BottomSheetDialog(this)
        mBottomSheetDialog!!.setContentView(view)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBottomSheetDialog!!.getWindow()?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }

        val button2: Button = view.findViewById(R.id.button6)
        button2.setOnClickListener {
           showInputDialog()
            mBottomSheetDialog!!.dismiss()
        }

        val button: Button = view.findViewById(R.id.button4)
        button.setOnClickListener {
            val intent = Intent(this, StokKeluarFormActivity::class.java)
            intent.putExtra("jenis_keluar", "2002")
            startActivity(intent)
            mBottomSheetDialog!!.dismiss()
        }


        mBottomSheetDialog!!.show()
        mBottomSheetDialog!!.setOnDismissListener(DialogInterface.OnDismissListener {
            mBottomSheetDialog = null
        })

    }

    fun addOutbound(view: View?) {
        startActivity(Intent(applicationContext, StokKeluarFormActivity::class.java))
    }


    fun cekCTplan(ctplan: String) {
        val serviceLogin = ServiceLogin(this)
        val service2 = Client.getClient().create(Sinkronasi::class.java)
            val call = service2.getSlugCT("Bearer " + serviceLogin.token, "CEKCTPLAN", ctplan)
            call.enqueue(object : Callback<CTPlan> {
                override fun onResponse(call: Call<CTPlan>, response: Response<CTPlan>) {

                    if (response.isSuccessful) {

                        if (response.body()?.total!! > 0){
                            Toast.makeText(
                                applicationContext,
                                "Sedang mengalihkan ke form outbound..",
                                Toast.LENGTH_LONG
                            ).show()

                            if(response.body()?.cek!! == 0){

                                submitData(response.body()?.ucode_ct.toString(), response.body()?.no_ct.toString(), response.body()?.ucode_gdg_asl.toString(), response.body()?.keterangan.toString())
//                                response.body()?.ucode_ct?.let { response.body()?.no_ct?.let { it1 ->
//                                    submitData(it,
//                                        it1
//                                    )
//                                } }


                            }else{
                                Toast.makeText(
                                    applicationContext,
                                    "Sudah ada CT PLAN.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }



                        }else{
                            Toast.makeText(
                                applicationContext,
                                "Tidak ada data CT Plan anda",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        Log.e("API Response", "Success")
                    } else {
                        Log.e("API Response", "Response failed with status: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<CTPlan>, t: Throwable) {
                   Log.e("API Error", "Request failed: ${t.message}")
                }
            })

    }


    private fun submitData(ucode_ct: String, no_ct: String, ucode_gdg: String, keterangan: String) {

        val serviceLogin = ServiceLogin(this)
        val serviceData = ServiceData(this)
        val service2 = ClientWMS.getClientWMS().create(
            Sinkronasi::class.java
        )

        val jsonData = """
          { 
            "ucode_ct": "",
            "tgl_outbound": "",
            "ucode_jenis_keluar": "2001",
            "ucode_gdg": "",
            "ucode_lokasi" : null,
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
        jsonObject.put("ucode_ct", ucode_ct)
        jsonObject.put("ucode_gdg", ucode_gdg)
        if (serviceData.ucode_gdg.equals("14010000000052")){
            jsonObject.put("factory", "NMT")
        }else{
            jsonObject.put("factory", "MMS")
        }
        jsonObject.put("keterangan", no_ct + " - " + keterangan )
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
//                        startActivity(Intent(applicationContext, StokKeluarDetailSJTBActivity::class.java))
                        val intent = Intent(applicationContext, StokKeluarDetailSJTBActivity::class.java)
                        intent.putExtra("ucode_ct", ucode_ct)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        applicationContext.startActivity(intent)
                    }
                } else {
                    Log.e("API Response", "Response failed with status: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<StokKeluarObject>, t: Throwable) {
                Log.e("API Error", "Request failed: ${t.message}")
            }
        })


    }


    fun setTimeout(delayMillis: Long, action: () -> Unit) {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(action, delayMillis)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(applicationContext, WarehouseActivity::class.java))
    }

}