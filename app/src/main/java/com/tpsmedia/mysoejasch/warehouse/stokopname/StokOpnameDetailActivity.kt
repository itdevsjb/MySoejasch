package com.tpsmedia.mysoejasch.warehouse.stokopname

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
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
import com.tpsmedia.mysoejasch.adapter.StokMasukAdapter
import com.tpsmedia.mysoejasch.adapter.StokOpnameDetailAdapter
import com.tpsmedia.mysoejasch.api.Client
import com.tpsmedia.mysoejasch.api.ClientWMS
import com.tpsmedia.mysoejasch.api.Interface
import com.tpsmedia.mysoejasch.api.Sinkronasi
import com.tpsmedia.mysoejasch.databinding.ActivityStokopnameDetailBinding
import com.tpsmedia.mysoejasch.model.ApiResponse
import com.tpsmedia.mysoejasch.model.CTPlanData.Success
import com.tpsmedia.mysoejasch.model.Warehouse.StokMasuk.StokMasuk
import com.tpsmedia.mysoejasch.model.Warehouse.StokMasukDetail.StokMasukDetail
import com.tpsmedia.mysoejasch.model.Warehouse.StokOpname.StokOpname
import com.tpsmedia.mysoejasch.model.Warehouse.StokOpnameDetail.StokOpnameDetail
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

class StokOpnameDetailActivity : AppCompatActivity(){

    private lateinit var binding: ActivityStokopnameDetailBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StokOpnameDetailAdapter
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
        binding = ActivityStokopnameDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ucode_opname = intent.getStringExtra("ucode_opname");

        if (ucode_opname != null) {
            getOpname(ucode_opname)
        }

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
        adapter = StokOpnameDetailAdapter(mutableListOf())
        recyclerView.adapter = adapter

//        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                if (!isLoading && isLastItemVisible()) {
//                    //loadData()
//                }
//            }
//        })

        //loadData()


    }

    private fun hideKeyboard(view: View) {
        val editText = view as? EditText
        editText?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }


    private fun getOpname(ucode_opname: String) {

        val serviceLogin = ServiceLogin(this)
        val serviceData = ServiceData(this)
        val service2 = Client.getClient(this).create(
            Sinkronasi::class.java
        )

        val call = service2.getOpnameId("Bearer "+ serviceLogin.token, ucode_opname)
        call.enqueue(object : Callback<StokOpname> {
            override fun onResponse(call: Call<StokOpname>, response: Response<StokOpname>) {
                if (response.isSuccessful) {
                    response.body()?.data?.let { dataList ->
                        val data = dataList[0]
                        initToolbar(data.no_opname, data.keterangan)

                        val sgSharedPref =
                            applicationContext.getSharedPreferences("data_mysoejasch_form", MODE_PRIVATE)
                        val editor = sgSharedPref.edit()
                        editor.putString("ucode_gdg", data.ucode_lokasi)
                        editor.putString("ucode_lokasi", data.ucode_lokasi)
                        editor.putString("ucode_lokasi", data.ucode_lokasi)
                        editor.putString("ucode_opname", ucode_opname)
                        editor.apply()

                        loadData(ucode_opname)

                    } ?: run {
                        Log.e("API Response", "No data found in response")
                    }

                } else {
                    Log.e("API Response", "Response Error")
                }
            }
            override fun onFailure(call: Call<StokOpname>, t: Throwable) {
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
                        val qrvalue = response.body()?.qr_value;
                        val serahterima = response.body()?.serahterima;
                        val no_box = response.body()?.no_box;
                        val ucode_opname = intent.getStringExtra("ucode_opname");


                        if (total != null) {
                            if(total > 0){
                                cekTerscan(ucode_opname.toString() , ucode_brg.toString(),ucode_sat.toString(), ucode_sat_std.toString(), batch_no.toString(), tgl_expired.toString(), qty.toString(), qty_std.toString(), serahterima.toString(), qrvalue.toString(), no_box.toString() );
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

            val call = service2.getSlug("Bearer "+ serviceLogin.token, "KodeQRCekOther", kodeqr)
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
                        val qrvalue = kodeqr;
                        val serahterima = "";
                        val no_box = "";
                        val ucode_opname = intent.getStringExtra("ucode_opname");


                        if (total != null) {
                            if(total > 0){
                                showQtyDialog( ucode_opname.toString() , ucode_brg.toString(),ucode_sat.toString(), ucode_sat_std.toString(), batch_no.toString(), tgl_expired.toString(), qty.toString(), qty_std.toString(), serahterima.toString(), qrvalue.toString(), no_box.toString()  );
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

           //
        }



    }

    @SuppressLint("MissingInflatedId")
    private fun showQtyDialog( ucode_opname: String, ucode_brg: String,ucode_sat: String, ucode_sat_std: String, batch_no: String, tgl_expired: String, qty: String, qty_std: String, serahterima: String, qrvalue: String, no_box:String ) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_qty_form, null)

        val qtyEditText: EditText = dialogView.findViewById(R.id.qtyEditText)
        val qtyStdEditText: EditText = dialogView.findViewById(R.id.qtyStdEditText)

        qtyEditText.setText(qty)
        qtyStdEditText.setText(qty_std)

        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Enter Quantities")
            .setView(dialogView)
            .setPositiveButton("Save") { dialog, _ ->
                val new_qty = qtyEditText.text.toString()
                val new_qtyStd = qtyStdEditText.text.toString()

                submitData(ucode_opname, ucode_brg,ucode_sat, ucode_sat_std, batch_no, tgl_expired, new_qty, new_qtyStd, serahterima, qrvalue, no_box )

                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        alertDialog.show()
    }

    private fun initToolbar(judul: String, keterangan: String) {
        binding.toolbar.setNavigationIcon(R.drawable.ic_menu)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = judul
        supportActionBar!!.subtitle = keterangan
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        Tools.setSystemBarColor(this)
    }



    private fun loadData(ucode_opname: String) {
        isLoading = true
        val serviceLogin = ServiceLogin(this)
        val service2 = Client.getClient(this).create(Sinkronasi::class.java)
        val call = service2.getOpnameDetailId(
            "Bearer " + serviceLogin.token,
            ucode_opname
        )
        call.enqueue(object : Callback<StokOpnameDetail> {
            override fun onResponse(
                call: Call<StokOpnameDetail>,
                response: Response<StokOpnameDetail>
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

            override fun onFailure(call: Call<StokOpnameDetail>, t: Throwable) {
                Log.e("API Error", "Request failed: ${t.message}")
                isLoading = false
            }
        })
    }

    private fun submitData(ucode_opname: String, ucode_brg: String,ucode_sat: String, ucode_sat_std: String, batch_no: String, tgl_expired: String, qty: String, qty_std: String, serahterima: String, qrvalue: String, no_box:String ) {

        val serviceLogin = ServiceLogin(this)
        val serviceData = ServiceData(this)
        val service2 = Client.getClient(this).create(
            Sinkronasi::class.java
        )

        val jsonData = """
            {
                "ucode_opname": "",
                "ucode_brg": "",
                "ucode_lokasi" : "",
                "ucode_pallete" : "",
                "ucode_sat" : "",
                "ucode_sat_std" : "",
                "sat_scan" : "BOX",
                "batch_no" : "",
                "tgl_expired" : "",
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
        jsonObject.put("ucode_opname", ucode_opname)
        jsonObject.put("ucode_lokasi", serviceData.ucode_lokasi)
        jsonObject.put("ucode_pallete", serviceData.ucode_pallet)
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

        val call = service2.postOpnameDetail("Bearer "+ serviceLogin.token, ucode_opname, requestBody)
        call.enqueue(object : Callback<StokOpname> {
            override fun onResponse(call: Call<StokOpname>, response: Response<StokOpname>) {
                if (response.body()?.success == true) {

                    Toast.makeText(applicationContext, "Success submit data", Toast.LENGTH_SHORT).show();
                    adapter.clearData()
                    currentPage = 1
                    loadData(ucode_opname)

                } else {
                    Log.e("API Response", "Response failed with status: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<StokOpname>, t: Throwable) {
                Log.e("API Error", "Request failed: ${t.message}")
            }
        })


    }


    private fun cekTerscan(ucode_opname: String, ucode_brg: String,ucode_sat: String, ucode_sat_std: String, batch_no: String, tgl_expired: String, qty: String, qty_std: String, serahterima: String, qrvalue: String, no_box:String ) {
        val serviceLogin = ServiceLogin(this)
        val service2 = Client.getClient(this).create(
            Sinkronasi::class.java
        )
        val call = service2.cekOpnameTerscan("Bearer "+ serviceLogin.token, ucode_opname, qrvalue)
        call.enqueue(object : Callback<Success> {
            override fun onResponse(call: Call<Success>, response: Response<Success>) {
                if (response.isSuccessful) {
                    if(response.body()?.total!! > 0){
                        Toast.makeText(applicationContext, "Qr Sudah Terscan", Toast.LENGTH_SHORT).show();
                    }else{
                        //Toast.makeText(applicationContext, "Qr Belum Terscan", Toast.LENGTH_SHORT).show();
                        submitData(ucode_opname, ucode_brg,ucode_sat, ucode_sat_std, batch_no, tgl_expired, qty, qty_std, serahterima, qrvalue, no_box )
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