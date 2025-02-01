package com.tpsmedia.mysoejasch.warehouse.stokkeluar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tpsmedia.materialx.ui.design.utils.Tools
import com.tpsmedia.mysoejasch.R
import com.tpsmedia.mysoejasch.adapter.StokKeluarDetailAdapter
import com.tpsmedia.mysoejasch.adapter.StokKeluarDetailCTAdapter
import com.tpsmedia.mysoejasch.api.Client
import com.tpsmedia.mysoejasch.api.ClientWMS
import com.tpsmedia.mysoejasch.api.Interface
import com.tpsmedia.mysoejasch.api.Sinkronasi
import com.tpsmedia.mysoejasch.databinding.ActivityStokkeluarDetailCtBinding
import com.tpsmedia.mysoejasch.model.ApiResponse
import com.tpsmedia.mysoejasch.model.CTPlanData.Success
import com.tpsmedia.mysoejasch.model.GetReponseSuccess
import com.tpsmedia.mysoejasch.model.Warehouse.CTPlan
import com.tpsmedia.mysoejasch.model.Warehouse.StokKeluar.StokKeluar
import com.tpsmedia.mysoejasch.model.Warehouse.StokKeluarDetail.StokKeluarDetail
import com.tpsmedia.mysoejasch.model.Warehouse.StokMasuk.StokMasuk
import com.tpsmedia.mysoejasch.pronline.PROnlineActivity
import com.tpsmedia.mysoejasch.service.ServiceData
import com.tpsmedia.mysoejasch.service.ServiceLogin
import com.tpsmedia.mysoejasch.warehouse.WarehouseActivity
import com.tpsmedia.mysoejasch.warehouse.stokmasuk.StokMasukFormActivity
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class StokKeluarDetailSJTBActivity : AppCompatActivity(){

    private lateinit var binding: ActivityStokkeluarDetailCtBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StokKeluarDetailCTAdapter
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
        binding = ActivityStokkeluarDetailCtBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etSearch.setOnFocusChangeListener { view, hasFocus ->
            hideKeyboard(view)
        }
//        val no_outbound = intent.getStringExtra("no_outbound");
//        if (no_outbound != null) {
//            initToolbar(no_outbound)
//        }

        val ucode_ct = intent.getStringExtra("ucode_ct");
        val no_ct = intent.getStringExtra("no_ct");
        if (ucode_ct != null) {
            getOutbound(ucode_ct.toString(), ucode_ct)
        }

        binding.etSearch.requestFocus()
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                runnable?.let { handler.removeCallbacks(it) }
                runnable = Runnable {
                    editable?.let {
                        if(!it.toString().equals("")){
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

        binding.etSearch.setOnFocusChangeListener { view, hasFocus ->
            hideKeyboard(view)
        }

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StokKeluarDetailCTAdapter(mutableListOf())
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!isLoading && isLastItemVisible()) {
                    //loadData()
                }
            }
        })


        binding.button.visibility = View.GONE;
        binding.buttonoke.visibility = View.VISIBLE;


        cekDataTerpenuhi(ucode_ct.toString(), ucode_ct.toString())
        binding.button.setOnClickListener {
            binding.button.isEnabled = false;
            binding.button.setText("Mohon Tunggu...")
            cekStokACTS(ucode_ct.toString(), ucode_ct.toString());
            //submitSJTB(ucode_ct.toString())
            Log.e("TEST", "Jalan");
        }



    }


    private fun getOutbound(ucode_ct: String, no_ct: String) {

        val serviceLogin = ServiceLogin(this)
        val serviceData = ServiceData(this)
        val service2 = Client.getClient(this).create(
            Interface::class.java
        )

        val call = service2.postDetailCT("Bearer "+ serviceLogin.token, ucode_ct)
        call.enqueue(object : Callback<Success> {
            override fun onResponse(call: Call<Success>, response: Response<Success>) {
                if (response.isSuccessful) {

                    response.body()?.data?.let { dataList ->
//                        val data = dataList[0]
                        initToolbar( response.body()?.no_ct.toString() )

                        val sgSharedPref =
                            applicationContext.getSharedPreferences("data_mysoejasch_form", MODE_PRIVATE)
                        val editor = sgSharedPref.edit()
                        editor.putString("ucode_outbound", response.body()?.ucode_outbound.toString())
                        editor.apply()

                        if(response.body()?.count_sjtb!! > 0){
                            binding.button.visibility = View.GONE;
                            binding.buttonoke.visibility = View.VISIBLE;
                        }
                        adapter.addData(dataList)
                        currentPage++

                    } ?: run {
                        Log.e("API Response", "No data found in response")
                    }

                } else {
                    Log.e("API Response", "Response Error")
                }
            }
            override fun onFailure(call: Call<Success>, t: Throwable) {
                Log.e("API Error", "Request failed: ${t.message}")
            }
        })

    }


    private fun cekDataTerpenuhi(ucode_ct: String, no_ct: String) {
        val serviceLogin = ServiceLogin(this)
        val service2 = Client.getClient(this).create(
            Interface::class.java
        )
        val call = service2.cekDataTerpenuhi("Bearer "+ serviceLogin.token, ucode_ct)
        call.enqueue(object : Callback<Success> {
            override fun onResponse(call: Call<Success>, response: Response<Success>) {
                if (response.isSuccessful) {
                    if(response.body()?.qty!! == 0){
                        if(response.body()?.cek_submit!! > 0){
                            binding.button.visibility = View.GONE;
                            binding.buttonoke.visibility = View.VISIBLE;
                        }else{
                            binding.button.visibility = View.VISIBLE;
                            binding.buttonoke.visibility = View.GONE;
                        }
                    }
                } else {
                    Log.e("API Response", "Response Error")
                }
            }
            override fun onFailure(call: Call<Success>, t: Throwable) {
                Log.e("API Error", "Request failed: ${t.message}")
            }
        })

    }

    private fun cekStokACTS(ucode_ct: String, no_ct: String) {
        val serviceLogin = ServiceLogin(this)
        val service2 = Client.getClient(this).create(
            Interface::class.java
        )
        val call = service2.cekDataStokACTS("Bearer "+ serviceLogin.token, ucode_ct)
        call.enqueue(object : Callback<Success> {
            override fun onResponse(call: Call<Success>, response: Response<Success>) {
                if (response.isSuccessful) {
                    if(response.body()?.total!! > 0){

                        val dialog = AlertDialog.Builder(this@StokKeluarDetailSJTBActivity)
                            .setTitle("Stok Minus ACTS")
                            // Ganti dengan tampilan yang sesuai, misalnya TextView
                            .setView(TextView(this@StokKeluarDetailSJTBActivity).apply { text = response.body()?.message })
                            .setPositiveButton("OK", null)
                            .setNegativeButton("Batal") { dialog, which ->
                                dialog.dismiss()
                            }
                            .create()
                        dialog.show()

                        binding.button.isEnabled = true;
                        binding.button.setText("SUBMIT SJTB")

                    }else{
                        submitSJTB(ucode_ct.toString())
                    }
                } else {
                    Log.e("API Response", "Response Error")
                }
            }
            override fun onFailure(call: Call<Success>, t: Throwable) {
                Log.e("API Error", "Request failed: ${t.message}")
            }
        })
    }

    private fun cekQr(kodeqr: String) {

        val serviceLogin = ServiceLogin(this)
        val service2 = Client.getClient(this).create(
            Sinkronasi::class.java
        )

        val resultArray = kodeqr.split(";")

        Log.e("ResultQr", resultArray.count().toString())

        if(resultArray.count() > 3){
            val ucode_ct = intent.getStringExtra("ucode_ct");
            val call = service2.getSlug("Bearer "+ serviceLogin.token, "KodeQRCekOutboundSementara", kodeqr + ";" + ucode_ct + ";")
            call.enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        val total = response.body()?.total;
                        val sisa = response.body()?.sisa;
                        val ucode_brg = response.body()?.ucode_brg2;
                        val ucode_sat = response.body()?.uCode_Sat2;
                        val ucode_sat_std = response.body()?.uCode_Sat_Std2;
                        val batch_no = response.body()?.batch_no2;
                        val tgl_expired = response.body()?.tgl_expired2;
                        val qty = response.body()?.qty2;
                        val qty_std = response.body()?.qty_std2;
                        val serahterima = response.body()?.serahterima;
                        val qrvalue = response.body()?.no_Karton;
                        val no_box = response.body()?.no_box;

                        val ucode_pallet = response.body()?.uCode_Pallet;


                        if (total != null) {
                            if(total > 0){
                                if (sisa != null) {
                                    if (sisa > 0){
                                        if (qty_std != null) {
                                            if(qty_std.toDouble() < 10){
                                                Toast.makeText(applicationContext, "Stok dengan BN yang anda pilih kurang di sistem", Toast.LENGTH_SHORT).show()
                                            }else{
                                                submitData(ucode_brg.toString(),ucode_sat.toString(), ucode_sat_std.toString(), batch_no.toString(), tgl_expired.toString(), qty.toString(), qty_std.toString(), serahterima.toString(), qrvalue.toString(), ucode_pallet.toString(), no_box.toString() )
                                            }
                                        }
                                    }else{
                                        Toast.makeText(applicationContext, "Barang anda sudah terpenuhi", Toast.LENGTH_SHORT).show()
                                    }
                                }
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
        }else{
            val ucode_ct = intent.getStringExtra("ucode_ct");
            val call = service2.getSlug("Bearer "+ serviceLogin.token, "KodeQRCekOutboundMini", kodeqr + ";" + ucode_ct + ";")
            call.enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        val total = response.body()?.total;
                        val sisa = response.body()?.sisa;
                        val ucode_brg = response.body()?.ucode_brg2;
                        val ucode_sat = response.body()?.uCode_Sat2;
                        val ucode_sat_std = response.body()?.uCode_Sat_Std2;
                        val batch_no = response.body()?.batch_no2;
                        val tgl_expired = response.body()?.tgl_expired2;
                        val qty = response.body()?.qty2;
                        val qty_std = response.body()?.qty_std2;
                        val serahterima = response.body()?.serahterima;
                        val qrvalue = response.body()?.no_Karton;
                        val no_box = response.body()?.no_box;
                        val ucode_pallet = response.body()?.uCode_Pallet;

                        if (total != null) {
                            if(total > 0){
                                if (sisa != null) {
                                    if (sisa > 0){
                                        if (qty_std != null) {
                                            if(qty_std.toDouble() < 10){
                                                Toast.makeText(applicationContext, "Stok dengan BN yang anda pilih kurang di sistem", Toast.LENGTH_SHORT).show()
                                            }else{
                                                submitData(ucode_brg.toString(),ucode_sat.toString(), ucode_sat_std.toString(), batch_no.toString(), tgl_expired.toString(), qty.toString(), qty_std.toString(), serahterima.toString(), qrvalue.toString(), ucode_pallet.toString(), no_box.toString() )
                                            }
                                        }
                                    }else{
                                        Toast.makeText(applicationContext, "Barang anda sudah terpenuhi", Toast.LENGTH_SHORT).show()
                                    }
                                }
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




    }

    private fun hideKeyboard(view: View) {
        val editText = view as? EditText
        editText?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    private fun submitData(ucode_brg: String,ucode_sat: String, ucode_sat_std: String, batch_no: String, tgl_expired: String, qty: String, qty_std: String, serahterima: String, qrvalue: String, ucode_pallet: String, no_box: String ) {

        val serviceLogin = ServiceLogin(this)
        val serviceData = ServiceData(this)
        val service2 = ClientWMS.getClientWMS(this).create(
            Sinkronasi::class.java
        )

        val jsonData = """
            {
                "ucode_brg": "",
                "ucode_lokasi" : "",
                "ucode_pallete" : "",
                "ucode_sat" : "",
                "ucode_sat_std" : "",
                "sat_scan" : "BOX",
                "batch_no" : "",
                "tgl_expired" : "",
                "qty_app" : 0,
                "qty_app_std" : 0,
                "qty" : 0,
                "qty_std" : 0,
                "created_by" : "",
                "no_box": "",
                "qr_value": ""
            }
        """
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // Anda bisa mengganti format sesuai kebutuhan
        val currentDate = dateFormat.format(Date())

        val jsonObject = JSONObject(jsonData)
        jsonObject.put("ucode_outbound", serviceData.ucode_outbound)
        jsonObject.put("ucode_lokasi", serviceData.ucode_lokasi)
        jsonObject.put("ucode_pallete", ucode_pallet )
        jsonObject.put("ucode_brg", ucode_brg)
        jsonObject.put("ucode_sat", ucode_sat)
        jsonObject.put("ucode_sat_std", ucode_sat_std)
        jsonObject.put("batch_no", batch_no)
        jsonObject.put("tgl_expired", tgl_expired)
        jsonObject.put("qty", qty)
        jsonObject.put("qty_std", qty_std)
        jsonObject.put("created_by", serviceLogin.loginName)

        jsonObject.put("no_box", no_box)
        jsonObject.put("qr_value", qrvalue)

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
                    val ucode_ct = intent.getStringExtra("ucode_ct");
                    getOutbound(ucode_ct.toString(), ucode_ct.toString())
                    cekDataTerpenuhi(ucode_ct.toString(), ucode_ct.toString())

                } else {
                    Log.e("API Response", "Response failed with status: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<StokKeluar>, t: Throwable) {
                Log.e("API Error", "Request failed: ${t.message}")
            }
        })


    }

    private fun submitSJTB(ucode_ct: String) {

        val serviceLogin = ServiceLogin(this)
        val serviceData = ServiceData(this)
        val token = serviceLogin.token;
        val service2 = Client.getClient(this).create(
            Interface::class.java
        )
        val call: Call<GetReponseSuccess> = service2.postSubmitSJTB(
            "Bearer $token",
            ucode_ct
        )
        call.enqueue(object : Callback<GetReponseSuccess?> {
            override fun onResponse(
                call: Call<GetReponseSuccess?>,
                response: Response<GetReponseSuccess?>
            ) {
                if (response.isSuccessful) {

                    Toast.makeText(
                        applicationContext,
                        "Berhasil",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent = Intent(applicationContext, StokKeluarDetailSJTBActivity::class.java)
                    intent.putExtra("ucode_ct", ucode_ct)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) // Menempatkan Activity yang sudah ada ke depan
                    applicationContext.startActivity(intent)

                } else {
                    Toast.makeText(
                        applicationContext,
                        "Gagal",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            override fun onFailure(call: Call<GetReponseSuccess?>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "Something went wrong...Please try later!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })



    }


    private fun initToolbar(judul: String) {
        binding.toolbar.setNavigationIcon(R.drawable.ic_menu)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = judul
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        Tools.setSystemBarColor(this)
    }



    private fun isLastItemVisible(): Boolean {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
        val totalItemCount = layoutManager.itemCount
        return lastVisibleItemPosition >= totalItemCount - 1
    }


    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(applicationContext, StokKeluarActivity::class.java))
    }





}