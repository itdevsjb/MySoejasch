package com.tpsmedia.mysoejasch.warehouse.stokmasuk

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import com.tpsmedia.mysoejasch.adapter.StokMasukAdapter
import com.tpsmedia.mysoejasch.adapter.StokMasukDetailAdapter
import com.tpsmedia.mysoejasch.api.Client
import com.tpsmedia.mysoejasch.api.ClientWMS
import com.tpsmedia.mysoejasch.api.Interface
import com.tpsmedia.mysoejasch.api.Sinkronasi
import com.tpsmedia.mysoejasch.databinding.ActivityStokmasukDetailBinding
import com.tpsmedia.mysoejasch.model.ApiResponse
import com.tpsmedia.mysoejasch.model.Warehouse.StokMasuk.StokMasuk
import com.tpsmedia.mysoejasch.model.Warehouse.StokMasukDetail.StokMasukDetail
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

class StokMasukDetailActivity : AppCompatActivity(){

    private lateinit var binding: ActivityStokmasukDetailBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StokMasukDetailAdapter
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
        binding = ActivityStokmasukDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ucode_inbound = intent.getStringExtra("ucode_inbound");

        if (ucode_inbound != null) {
            getInbound(ucode_inbound)
        }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                runnable?.let { handler.removeCallbacks(it) }
                runnable = Runnable {
                    editable?.let {
                        if(!it.toString().equals("")){
                            val jenis_terima = intent.getStringExtra("jenis_masuk");
                            Log.e("CEKSISTEM", jenis_terima.toString())
                            if (jenis_terima.equals("1001")){
                                cekInbound(it.toString())
                            }else{
                                cekQr(it.toString())
                            }

                        }
                        binding.etSearch.text.clear()
                        binding.etSearch.requestFocus()
                    }
                }
                handler.postDelayed(runnable!!, 1500) // Delay 1 detik
            }
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
//        binding.etSearch.requestFocus()
        binding.etSearch.setOnFocusChangeListener { view, hasFocus ->
            hideKeyboard(view)
        }


        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StokMasukDetailAdapter(mutableListOf())
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

    private fun hideKeyboard(view: View) {
        val editText = view as? EditText
        editText?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }


    private fun getInbound(ucode_inbound: String) {

        val serviceLogin = ServiceLogin(this)
        val serviceData = ServiceData(this)
        val service2 = ClientWMS.getClientWMS(this).create(
            Sinkronasi::class.java
        )

        val call = service2.getInboundId("Bearer "+ serviceLogin.token, ucode_inbound)
        call.enqueue(object : Callback<StokMasuk> {
            override fun onResponse(call: Call<StokMasuk>, response: Response<StokMasuk>) {
                if (response.isSuccessful) {

                    response.body()?.data?.let { dataList ->
                        val data = dataList[0]
                       initToolbar(data.no_inbound, data.keterangan)

                        val sgSharedPref =
                            applicationContext.getSharedPreferences("data_mysoejasch_form", MODE_PRIVATE)
                        val editor = sgSharedPref.edit()
                        editor.putString("ucode_lokasi", data.ucode_lokasi)
                        editor.putString("ucode_inbound", ucode_inbound)
                        editor.apply()


                    } ?: run {
                        Log.e("API Response", "No data found in response")
                    }

                } else {
                    Log.e("API Response", "Response Error")
                }
            }
            override fun onFailure(call: Call<StokMasuk>, t: Throwable) {
                Log.e("API Error", "Request failed: ${t.message}")
            }
        })

    }

    private fun cekInbound(kodeqr: String) {

        val serviceLogin = ServiceLogin(this)
        val service2 = Client.getClient(this).create(
            Sinkronasi::class.java
        )
        val call = service2.getSlug("Bearer "+ serviceLogin.token, "CekScanInbound", kodeqr)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val total = response.body()?.total;
                    if (total != null) {
                        if(total > 0){
                            Toast.makeText(applicationContext, "Box yang anda scan sudah ada didalam data inventory", Toast.LENGTH_SHORT).show()
                        }else{
                            cekQr(kodeqr)
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
                    val no_box = response.body()?.no_box;


                    if (total != null) {
                        if(total > 0){
                            submitData(ucode_brg.toString(),ucode_sat.toString(), ucode_sat_std.toString(), batch_no.toString(), tgl_expired.toString(), qty.toString(), qty_std.toString(), serahterima.toString(), qrvalue.toString(), no_box.toString() )
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
        val ucode_inbound = intent.getStringExtra("ucode_inbound")
        val serviceLogin = ServiceLogin(this)
        val service2 = ClientWMS.getClientWMS(this).create(Sinkronasi::class.java)
        val call = service2.getDetailInbound(
            "Bearer " + serviceLogin.token,
            ucode_inbound,
            currentPage.toString(),
            limit.toString()
        )
        call.enqueue(object : Callback<StokMasukDetail> {
            override fun onResponse(
                call: Call<StokMasukDetail>,
                response: Response<StokMasukDetail>
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

            override fun onFailure(call: Call<StokMasukDetail>, t: Throwable) {
                Log.e("API Error", "Request failed: ${t.message}")
                isLoading = false
            }
        })
    }

    private fun submitData(ucode_brg: String,ucode_sat: String, ucode_sat_std: String, batch_no: String, tgl_expired: String, qty: String, qty_std: String, serahterima: String, qrvalue: String, no_box:String ) {

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
                "qty_st" : 0,
                "qty_st_std" : 0,
                "qty" : 0,
                "qty_std" : 0,
                "ucode_st" : "",
                "urutan_cetak" : "",
                "created_by" : "",
                "no_urut" : ""
            }
        """
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // Anda bisa mengganti format sesuai kebutuhan
        val currentDate = dateFormat.format(Date())

        val jsonObject = JSONObject(jsonData)
        jsonObject.put("ucode_inbound", serviceData.ucode_inbound)
        jsonObject.put("ucode_lokasi", serviceData.ucode_lokasi)
        jsonObject.put("ucode_brg", ucode_brg)
        jsonObject.put("ucode_sat", ucode_sat)
        jsonObject.put("ucode_sat_std", ucode_sat_std)
        jsonObject.put("batch_no", batch_no)
        jsonObject.put("tgl_expired", tgl_expired)
        jsonObject.put("qty", qty)
        jsonObject.put("qty_std", qty_std)
        jsonObject.put("ucode_st", serahterima)
        jsonObject.put("urutan_cetak", qrvalue)
        jsonObject.put("no_urut", no_box)
        jsonObject.put("created_by", serviceLogin.loginName)

        val requestBody = RequestBody.create(
            MediaType.parse("application/json; charset=utf-8"),
            jsonObject.toString()
        )

        val call = service2.postInboundDetail("Bearer "+ serviceLogin.token, serviceData.ucode_inbound, requestBody)
        call.enqueue(object : Callback<StokMasuk> {
            override fun onResponse(call: Call<StokMasuk>, response: Response<StokMasuk>) {
                if (response.isSuccessful) {

                    Toast.makeText(applicationContext, "Success submit data", Toast.LENGTH_SHORT).show();
                    adapter.clearData()
                    currentPage = 1
                    loadData()


                } else {
                    Log.e("API Response", "Response failed with status: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<StokMasuk>, t: Throwable) {
                Log.e("API Error", "Request failed: ${t.message}")
            }
        })


    }


    fun setTimeout(delayMillis: Long, action: () -> Unit) {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(action, delayMillis)
    }

    private fun isLastItemVisible(): Boolean {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
        val totalItemCount = layoutManager.itemCount
        return lastVisibleItemPosition >= totalItemCount - 1
    }




}