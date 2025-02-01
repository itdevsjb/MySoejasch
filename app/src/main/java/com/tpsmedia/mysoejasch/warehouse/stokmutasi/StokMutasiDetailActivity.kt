package com.tpsmedia.mysoejasch.warehouse.stokmutasi

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tpsmedia.materialx.ui.design.utils.Tools
import com.tpsmedia.mysoejasch.R
import com.tpsmedia.mysoejasch.adapter.StokMutasiAdapter
import com.tpsmedia.mysoejasch.adapter.StokMasukDetailAdapter
import com.tpsmedia.mysoejasch.adapter.StokMutasiDetailAdapter
import com.tpsmedia.mysoejasch.api.ClientWMS
import com.tpsmedia.mysoejasch.api.Sinkronasi
import com.tpsmedia.mysoejasch.databinding.ActivityStokmutasiDetailBinding
import com.tpsmedia.mysoejasch.model.Warehouse.StokMutasi.StokMutasi
import com.tpsmedia.mysoejasch.model.Warehouse.StokMutasiDetail.StokMutasiDetail
import com.tpsmedia.mysoejasch.service.ServiceLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StokMutasiDetailActivity : AppCompatActivity(){

    private lateinit var binding: ActivityStokmutasiDetailBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StokMutasiDetailAdapter
    private var currentPage = 1
    private var isLoading = false
    private val limit = 20


    private val mBehavior: BottomSheetBehavior<*>? = null
    private var mBottomSheetDialog: BottomSheetDialog? = null
    private val bottom_sheet: View? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStokmutasiDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val no_mutasi = intent.getStringExtra("no_mutasi");
        if (no_mutasi != null) {
            initToolbar(no_mutasi)
        }

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StokMutasiDetailAdapter(mutableListOf())
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!isLoading && isLastItemVisible()) {
                    loadData()
                }
            }
        })

        loadData()


    }

    private fun initToolbar(judul: String) {
        binding.toolbar.setNavigationIcon(R.drawable.ic_menu)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = judul
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        Tools.setSystemBarColor(this)
    }

    private fun loadData() {
        isLoading = true
        val ucode_mutasi = intent.getStringExtra("ucode_mutasi")
        val serviceLogin = ServiceLogin(this)
        val service2 = ClientWMS.getClientWMS(this).create(Sinkronasi::class.java)
        val call = service2.getDetailMutasi(
            "Bearer " + serviceLogin.token,
            ucode_mutasi,
            currentPage.toString(),
            limit.toString()
        )
        call.enqueue(object : Callback<StokMutasiDetail> {
            override fun onResponse(
                call: Call<StokMutasiDetail>,
                response: Response<StokMutasiDetail>
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

            override fun onFailure(call: Call<StokMutasiDetail>, t: Throwable) {
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