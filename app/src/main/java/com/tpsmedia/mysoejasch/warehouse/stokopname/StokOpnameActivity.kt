package com.tpsmedia.mysoejasch.warehouse.stokopname

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
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
import com.tpsmedia.materialx.ui.design.utils.Tools.dpToPx
import com.tpsmedia.mysoejasch.R
import com.tpsmedia.mysoejasch.adapter.StokOpnameAdapter
import com.tpsmedia.mysoejasch.api.Client
import com.tpsmedia.mysoejasch.api.ClientWMS
import com.tpsmedia.mysoejasch.api.Interface
import com.tpsmedia.mysoejasch.api.Sinkronasi
import com.tpsmedia.mysoejasch.databinding.ActivityStokopnameBinding
import com.tpsmedia.mysoejasch.model.Warehouse.StokMasuk.StokMasuk
import com.tpsmedia.mysoejasch.model.Warehouse.StokOpname.StokOpname
import com.tpsmedia.mysoejasch.service.ServiceData
import com.tpsmedia.mysoejasch.service.ServiceLogin
import com.tpsmedia.mysoejasch.warehouse.WarehouseActivity
import com.tpsmedia.mysoejasch.warehouse.kartustok.KartuStockActivity
import com.tpsmedia.mysoejasch.warehouse.location.LocationActivity
import com.tpsmedia.mysoejasch.warehouse.pallet.PalletActivity
import com.tpsmedia.mysoejasch.warehouse.produksi.ProduksiActivity
import com.tpsmedia.mysoejasch.warehouse.stokbarang.StokBarangActivity
import com.tpsmedia.mysoejasch.warehouse.stokkeluar.StokKeluarActivity
import com.tpsmedia.mysoejasch.warehouse.stokmasuk.StokMasukFormActivity
import com.tpsmedia.mysoejasch.warehouse.stokmutasi.StokMutasiActivity

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StokOpnameActivity : AppCompatActivity()  {

    private lateinit var binding: ActivityStokopnameBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StokOpnameAdapter
    private var currentPage = 1
    private var isLoading = false
    private val limit = 20

    private val mBehavior: BottomSheetBehavior<*>? = null
    private var mBottomSheetDialog: BottomSheetDialog? = null
    private val bottom_sheet: View? = null

    private var drawerLayout: DrawerLayout? = null
    var searchKeyword = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStokopnameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initToolbar()

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StokOpnameAdapter(mutableListOf())
        recyclerView.adapter = adapter

//        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                if (!isLoading && isLastItemVisible()) {
//                    loadData()
//                }
//            }
//        })

        binding.swipeRefresh.setOnRefreshListener {
            searchKeyword = ""
            currentPage = 1
            adapter.clearData()
        }

        loadData()

        drawerLayout = binding.drawerLayout
        val navigationView = findViewById<NavigationView>(R.id.nav_view)

        val menu = navigationView.menu
        val navHide = menu.findItem(R.id.nav_inbound)
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
                    //startActivity(Intent(applicationContext, StokMasukActivity::class.java))
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
        supportActionBar!!.title = "Stock Opname"
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
        val service2 = Client.getClient(this).create(
            Sinkronasi::class.java
        )
        val call = service2.getOpname("Bearer "+ serviceLogin.token, currentPage.toString(), limit.toString(), serviceData.start_date ,serviceData.end_date, searchKeyword)
        call.enqueue(object : Callback<StokOpname> {
            override fun onResponse(call: Call<StokOpname>, response: Response<StokOpname>) {
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
            override fun onFailure(call: Call<StokOpname>, t: Throwable) {
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

    fun showForm(view: View?){
        val intent = Intent(this, StokOpnameFormActivity::class.java)
        startActivity(intent)
    }


//    fun showBottomSheetDialog(view: View?) {
//        if (mBehavior?.getState()  === BottomSheetBehavior.STATE_EXPANDED) {
//            mBehavior?.setState(BottomSheetBehavior.STATE_COLLAPSED)
//        }
//
//        val view: View = layoutInflater.inflate(R.layout.sheet_list, null)
//        mBottomSheetDialog = BottomSheetDialog(this)
//        mBottomSheetDialog!!.setContentView(view)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            mBottomSheetDialog!!.getWindow()?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//        }
//
//        val button: Button = view.findViewById(R.id.button5)
//        button.setOnClickListener {
//            val intent = Intent(this, StokMasukFormActivity::class.java)
//            intent.putExtra("jenis_masuk", "1002")
//            startActivity(intent)
//            mBottomSheetDialog!!.dismiss()
//        }
//
//        val button2: Button = view.findViewById(R.id.button4)
//        button2.setOnClickListener {
//            val intent = Intent(this, StokMasukFormActivity::class.java)
//            intent.putExtra("jenis_masuk", "1003")
//            startActivity(intent)
//            mBottomSheetDialog!!.dismiss()
//        }
//
//        val button3: Button = view.findViewById(R.id.button6)
//        button3.setOnClickListener {
//            //cekHPS()
//            mBottomSheetDialog!!.dismiss()
//        }
//
//        mBottomSheetDialog!!.show()
//        mBottomSheetDialog!!.setOnDismissListener(DialogInterface.OnDismissListener {
//            mBottomSheetDialog = null
//        })
//
//    }

    fun setTimeout(delayMillis: Long, action: () -> Unit) {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(action, delayMillis)
    }

    fun addInbound(view: View?) {
        startActivity(Intent(applicationContext, StokMasukFormActivity::class.java))
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(applicationContext, WarehouseActivity::class.java))
    }


}