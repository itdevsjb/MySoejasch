package com.tpsmedia.mysoejasch.warehouse.stokkeluar

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tpsmedia.materialx.ui.design.utils.Tools
import com.tpsmedia.mysoejasch.R
import com.tpsmedia.mysoejasch.adapter.StokKeluarDetailAdapter
import com.tpsmedia.mysoejasch.api.Client
import com.tpsmedia.mysoejasch.api.ClientWMS
import com.tpsmedia.mysoejasch.api.Sinkronasi
import com.tpsmedia.mysoejasch.databinding.ActivityStokkeluarDetailBinding
import com.tpsmedia.mysoejasch.model.ApiResponse
import com.tpsmedia.mysoejasch.model.Warehouse.StokKeluar.StokKeluar
import com.tpsmedia.mysoejasch.model.Warehouse.StokKeluarDetail.StokKeluarDetail
import com.tpsmedia.mysoejasch.model.Warehouse.StokMasuk.StokMasuk
import com.tpsmedia.mysoejasch.service.ServiceData
import com.tpsmedia.mysoejasch.service.ServiceLogin
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class StokKeluarDetailActivity : AppCompatActivity(){

    private lateinit var binding: ActivityStokkeluarDetailBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StokKeluarDetailAdapter
    private var currentPage = 1
    private var isLoading = false
    private val limit = 20


    private val mBehavior: BottomSheetBehavior<*>? = null
    private var mBottomSheetDialog: BottomSheetDialog? = null
    private val bottom_sheet: View? = null

    private val handler = Handler()
    private var runnable: Runnable? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStokkeluarDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etSearch.setOnFocusChangeListener { view, hasFocus ->
                hideKeyboard(view)
        }
//        val no_outbound = intent.getStringExtra("no_outbound");
//        if (no_outbound != null) {
//            initToolbar(no_outbound)
//        }

        val ucode_outbound = intent.getStringExtra("ucode_outbound");
        if (ucode_outbound != null) {
            getOutbound(ucode_outbound)
        }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                runnable?.let { handler.removeCallbacks(it) }
                runnable = Runnable {
                    editable?.let {
                        if(!it.toString().equals("")){
                            //Toast.makeText(applicationContext, "Teks selesai: ${it.toString()}", Toast.LENGTH_SHORT).show()

                            cekQr(it.toString())


                        }
                        binding.etSearch.text.clear()
                        binding.etSearch.requestFocus()
                    }
                }
                handler.postDelayed(runnable!!, 1000) // Delay 1 detik
            }
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StokKeluarDetailAdapter(mutableListOf())
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!isLoading && isLastItemVisible()) {
                    //loadData()
                }
            }
        })

        loadData()

    }


    private fun getOutbound(ucode_outbound: String) {

        val serviceLogin = ServiceLogin(this)
        val serviceData = ServiceData(this)
        val service2 = ClientWMS.getClientWMS(this).create(
            Sinkronasi::class.java
        )

        val call = service2.getOutboundId("Bearer "+ serviceLogin.token, ucode_outbound)
        call.enqueue(object : Callback<StokKeluar> {
            override fun onResponse(call: Call<StokKeluar>, response: Response<StokKeluar>) {
                if (response.isSuccessful) {

                    response.body()?.data?.let { dataList ->
                        val data = dataList[0]
                        initToolbar(data.no_outbound, data.keterangan)

                        val sgSharedPref =
                            applicationContext.getSharedPreferences("data_mysoejasch_form", MODE_PRIVATE)
                        val editor = sgSharedPref.edit()
                        editor.putString("ucode_lokasi", data.ucode_lokasi)
                        editor.putString("ucode_outbound", ucode_outbound)
                        editor.apply()


                    } ?: run {
                        Log.e("API Response", "No data found in response")
                    }

                } else {
                    Log.e("API Response", "Response Error")
                }
            }
            override fun onFailure(call: Call<StokKeluar>, t: Throwable) {
                Log.e("API Error", "Request failed: ${t.message}")
            }
        })

    }

    private fun cekQr(kodeqr: String) {

        val serviceLogin = ServiceLogin(this)
        val service2 = Client.getClient(this).create(
            Sinkronasi::class.java
        )

        val call = service2.getSlug("Bearer "+ serviceLogin.token, "KodeQRCek", kodeqr)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val total = response.body()?.total;
                    val ucode_brg = response.body()?.ucode_brg;
                    val ucode_sat = response.body()?.ucode_sat;
                    val ucode_sat_std = response.body()?.ucode_sat_std;
                    val batch_no = response.body()?.batch_no;
                    val tgl_expired = response.body()?.tgl_expired;
                    val qty = response.body()?.qty;
                    val qty_std = response.body()?.qty_std;
                    val serahterima = response.body()?.serahterima;
                    val qrvalue = response.body()?.qr_value;


                    if (total != null) {
                        if(total > 0){
                            submitData(ucode_brg.toString(),ucode_sat.toString(), ucode_sat_std.toString(), batch_no.toString(), tgl_expired.toString(), qty.toString(), qty_std.toString(), serahterima.toString(), qrvalue.toString() )
                            //Toast.makeText(applicationContext, "QR Terdaftar", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(applicationContext, "QR Tidak Terdaftar", Toast.LENGTH_SHORT).show()
                        }
                    }
                    Log.e("API Success", "TOTAL " + total.toString())
                } else {
                    Log.e("API Response", "GAGAL TOTAL")
                }
            }
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("API Error", "Request failed: ${t.message}")
            }
        })

    }

    private fun hideKeyboard(view: View) {
        val editText = view as? EditText
        editText?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    private fun submitData(ucode_brg: String,ucode_sat: String, ucode_sat_std: String, batch_no: String, tgl_expired: String, qty: String, qty_std: String, serahterima: String, qrvalue: String ) {

        val serviceLogin = ServiceLogin(this)
        val serviceData = ServiceData(this)
        val service2 = ClientWMS.getClientWMS(this).create(
            Sinkronasi::class.java
        )

        val jsonData = """
            {
                "ucode_brg": "",
                "ucode_lokasi" : "",
                "ucode_pallet" : "",
                "ucode_sat" : "",
                "ucode_sat_std" : "",
                "sat_scan" : "BOX",
                "batch_no" : "",
                "tgl_expired" : "",
                "qty_app" : 0,
                "qty_app_std" : 0,
                "qty" : 0,
                "qty_std" : 0,
                "created_by" : ""
            }
        """
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // Anda bisa mengganti format sesuai kebutuhan
        val currentDate = dateFormat.format(Date())

        val jsonObject = JSONObject(jsonData)
        jsonObject.put("ucode_outbound", serviceData.ucode_outbound)
        jsonObject.put("ucode_lokasi", serviceData.ucode_lokasi)
        jsonObject.put("ucode_brg", ucode_brg)
        jsonObject.put("ucode_sat", ucode_sat)
        jsonObject.put("ucode_sat_std", ucode_sat_std)
        jsonObject.put("batch_no", batch_no)
        jsonObject.put("tgl_expired", tgl_expired)
        jsonObject.put("qty", qty)
        jsonObject.put("qty_std", qty_std)
        jsonObject.put("created_by", serviceLogin.loginName)

        val requestBody = RequestBody.create(
            MediaType.parse("application/json; charset=utf-8"),
            jsonObject.toString()
        )

        val call = service2.postOutboundDetail("Bearer "+ serviceLogin.token, serviceData.ucode_outbound, requestBody)
        call.enqueue(object : Callback<StokKeluar> {
            override fun onResponse(call: Call<StokKeluar>, response: Response<StokKeluar>) {
                if (response.isSuccessful) {

                    Toast.makeText(applicationContext, "Success submit data", Toast.LENGTH_SHORT).show();
                    adapter.clearData()
                    currentPage = 1
                    loadData()


                } else {
                    Log.e("API Response", "Response failed with status: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<StokKeluar>, t: Throwable) {
                Log.e("API Error", "Request failed: ${t.message}")
            }
        })


    }


    private fun initToolbar(judul: String, keterangan: String) {
        binding.toolbar.setNavigationIcon(R.drawable.ic_menu)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = judul
        supportActionBar!!.subtitle = keterangan
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        Tools.setSystemBarColor(this)
    }

    private fun loadData() {
        isLoading = true
        val ucode_outbound = intent.getStringExtra("ucode_outbound")
        val serviceLogin = ServiceLogin(this)
        val service2 = ClientWMS.getClientWMS(this).create(Sinkronasi::class.java)
        val call = service2.getDetailOutbound(
            "Bearer " + serviceLogin.token,
            ucode_outbound,
            currentPage.toString(),
            limit.toString()
        )
        call.enqueue(object : Callback<StokKeluarDetail> {
            override fun onResponse(
                call: Call<StokKeluarDetail>,
                response: Response<StokKeluarDetail>
            ) {
                if (response.isSuccessful) {
                    response.body()?.data?.let { data ->
                        if (data.isNotEmpty()) {
                            adapter.addData(data)
                            currentPage++
                        } else {
                            Log.i("API Response", "No more data to load")
                        }
                    } ?: run {
                        Log.e("API Response", "No data found in response")
                    }
                } else {
                    Log.e("API Response", "Response failed with status: ${response.code()}")
                }
                isLoading = false
            }

            override fun onFailure(call: Call<StokKeluarDetail>, t: Throwable) {
                Log.e("API Error", "Request failed: ${t.message}")
                isLoading = false
            }
        })
    }

    private fun isLastItemVisible(): Boolean {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
        val totalItemCount = layoutManager.itemCount
        return lastVisibleItemPosition >= totalItemCount - 1
    }






}