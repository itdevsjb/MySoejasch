package com.tpsmedia.mysoejasch.poonline;

import android.graphics.PorterDuff
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.tpsmedia.materialx.ui.design.utils.Tools
import com.tpsmedia.mysoejasch.R
import com.tpsmedia.mysoejasch.api.Client
import com.tpsmedia.mysoejasch.api.Sinkronasi
import com.tpsmedia.mysoejasch.databinding.ActivityPohistoryBinding
import com.tpsmedia.mysoejasch.databinding.ItemHistoryBinding
import com.tpsmedia.mysoejasch.model.Approval.ApprovalList
import com.tpsmedia.mysoejasch.model.CardData
import com.tpsmedia.mysoejasch.service.SQLiteHelper
import com.tpsmedia.mysoejasch.service.ServiceLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HistoryPOActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPohistoryBinding
    private lateinit var dbHelper: SQLiteHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPohistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbHelper = SQLiteHelper(this)
        initToolbar();

        val approval_1 = intent.getStringExtra("approval_1");
        val approval_2 = intent.getStringExtra("approval_2");
        val approval_3 = intent.getStringExtra("approval_3");
        val approval_4 = intent.getStringExtra("approval_4");

        val no_approval_1 = intent.getStringExtra("no_approval_1");
        val no_approval_2 = intent.getStringExtra("no_approval_2");
        val no_approval_3 = intent.getStringExtra("no_approval_3");
        val no_approval_4 = intent.getStringExtra("no_approval_4");

        val service = Client.getClient().create(
            Sinkronasi::class.java
        )

        val serviceLogin = ServiceLogin(this)

        if(no_approval_1.toString() != "null"){
            val call: Call<ApprovalList> = service.postApproval("Bearer " + serviceLogin.getToken(), no_approval_1)
            call.enqueue(object : Callback<ApprovalList> {
                override fun onResponse(
                    call: Call<ApprovalList>,
                    response: Response<ApprovalList>
                ) {
                    if (response.body()!!.data.isNotEmpty()) {
                        for (data in response.body()!!.data) {
                            val db = dbHelper.writableDatabase
                            db.execSQL(
                                "INSERT OR REPLACE INTO tb_mt_approval (no_approval, approval_tahap, nama_karyawan, status, user_agent, latitude, longitude, remarks, tanggal_jam ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                                arrayOf<Any>(
                                    data.no_approval,
                                    data.approval_tahap,
                                    data.nama_karyawan,
                                    data.status,
                                    data.user_agent ?: "",
                                    data.latitude ?: "",
                                    data.longitude ?: "",
                                    data.remarks ?: "",
                                    data.tanggal + ' ' + data.jam
                                )
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<ApprovalList>, t: Throwable) {
                }
            })
        }
        if(no_approval_2.toString() != "null"){
            val call: Call<ApprovalList> = service.postApproval("Bearer " + serviceLogin.getToken(), no_approval_2)
            call.enqueue(object : Callback<ApprovalList> {
                override fun onResponse(
                    call: Call<ApprovalList>,
                    response: Response<ApprovalList>
                ) {
                    if (response.body()!!.data.isNotEmpty()) {
                        for (data in response.body()!!.data) {
                            val db = dbHelper.writableDatabase
                            db.execSQL(
                                "INSERT OR REPLACE INTO tb_mt_approval (no_approval, approval_tahap, nama_karyawan, status, user_agent, latitude, longitude, remarks ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                                arrayOf<Any>(
                                    data.no_approval,
                                    data.approval_tahap,
                                    data.nama_karyawan,
                                    data.status,
                                    data.user_agent ?: "",
                                    data.latitude ?: "",
                                    data.longitude ?: "",
                                    data.remarks ?: ""
                                )
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<ApprovalList>, t: Throwable) {
                }
            })
        }
        if(no_approval_3.toString() != "null"){
            val call: Call<ApprovalList> = service.postApproval("Bearer " + serviceLogin.getToken(), no_approval_3)
            call.enqueue(object : Callback<ApprovalList> {
                override fun onResponse(
                    call: Call<ApprovalList>,
                    response: Response<ApprovalList>
                ) {
                    if (response.body()!!.data.isNotEmpty()) {
                        for (data in response.body()!!.data) {
                            val db = dbHelper.writableDatabase
                            db.execSQL(
                                "INSERT OR REPLACE INTO tb_mt_approval (no_approval, approval_tahap, nama_karyawan, status, user_agent, latitude, longitude, remarks ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                                arrayOf<Any>(
                                    data.no_approval,
                                    data.approval_tahap,
                                    data.nama_karyawan,
                                    data.status,
                                    data.user_agent ?: "",
                                    data.latitude ?: "",
                                    data.longitude ?: "",
                                    data.remarks ?: ""
                                )
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<ApprovalList>, t: Throwable) {
                }
            })
        }
        if(approval_4 != null){
            if(no_approval_4.toString() != "null"){
                val call: Call<ApprovalList> = service.postApproval("Bearer " + serviceLogin.getToken(), no_approval_4)
                call.enqueue(object : Callback<ApprovalList> {
                    override fun onResponse(
                        call: Call<ApprovalList>,
                        response: Response<ApprovalList>
                    ) {
                        if (response.body()!!.data.isNotEmpty()) {
                            for (data in response.body()!!.data) {
                                val db = dbHelper.writableDatabase
                                db.execSQL(
                                    "INSERT OR REPLACE INTO tb_mt_approval (no_approval, approval_tahap, nama_karyawan, status, user_agent, latitude, longitude, remarks ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                                    arrayOf<Any>(
                                        data.no_approval,
                                        data.approval_tahap,
                                        data.nama_karyawan,
                                        data.status,
                                        data.user_agent ?: "",
                                        data.latitude ?: "",
                                        data.longitude ?: "",
                                        data.remarks ?: ""
                                    )
                                )
                            }
                        }
                    }

                    override fun onFailure(call: Call<ApprovalList>, t: Throwable) {
                    }
                })
            }
        }


        val cek_approval_1 = intent.getStringExtra("cek_approval_1");
        val cek_approval_2 = intent.getStringExtra("cek_approval_2");
        val cek_approval_3 = intent.getStringExtra("cek_approval_3");
        val cek_approval_4 = intent.getStringExtra("cek_approval_4");

        if(approval_4 != null){
            val dataList = listOf(
                CardData(dbHelper.getNameEmployee(this, approval_1), no_approval_1.toString(), dbHelper.getWaktuApproval(this, no_approval_1.toString()), cek_approval_1 ?: "0"),
                CardData(dbHelper.getNameEmployee(this, approval_2), no_approval_2.toString(), dbHelper.getWaktuApproval(this, no_approval_2.toString()), cek_approval_2 ?: "0"),
                CardData(dbHelper.getNameEmployee(this, approval_3), no_approval_3.toString(), dbHelper.getWaktuApproval(this, no_approval_3.toString()), cek_approval_3 ?: "0"),
                CardData(dbHelper.getNameEmployee(this, approval_4), no_approval_4.toString(), dbHelper.getWaktuApproval(this, no_approval_4.toString()), cek_approval_4 ?: "0")
            )

            for (data in dataList) {
                val itemBinding = ItemHistoryBinding.inflate(layoutInflater)
                itemBinding.nameTextView.text = data.name
                if(data.location.equals("null")){
                    itemBinding.locationTextView.text = ""
                }else{
                    itemBinding.locationTextView.text = "${data.location}"
                }

                itemBinding.timeTextView.text = data.time

                val SuccessColor = ContextCompat.getColor(this, R.color.green_800)
                val PendingColor = ContextCompat.getColor(this, R.color.amber_300)
                val CancelColor = ContextCompat.getColor(this, R.color.red_800)
                if(data.cek.equals("1")){
                    itemBinding.hasilcek.setColorFilter(SuccessColor, PorterDuff.Mode.SRC_IN)
                }else if(data.cek.equals("2")){
                    itemBinding.hasilcek.setColorFilter(CancelColor, PorterDuff.Mode.SRC_IN)
                }else{
                    itemBinding.hasilcek.setColorFilter(PendingColor, PorterDuff.Mode.SRC_IN)
                }

                binding.layoutData.addView(itemBinding.root)
            }

        }else{
            val dataList = listOf(
                CardData(dbHelper.getNameEmployee(this, approval_1), no_approval_1.toString(), dbHelper.getWaktuApproval(this, no_approval_1.toString()), cek_approval_1 ?: "0"),
                CardData(dbHelper.getNameEmployee(this, approval_2), no_approval_2.toString(), dbHelper.getWaktuApproval(this, no_approval_2.toString()), cek_approval_2 ?: "0"),
                CardData(dbHelper.getNameEmployee(this, approval_3), no_approval_3.toString(), dbHelper.getWaktuApproval(this, no_approval_3.toString()), cek_approval_3 ?: "0")

            )

            for (data in dataList) {
                val itemBinding = ItemHistoryBinding.inflate(layoutInflater)
                itemBinding.nameTextView.text = data.name
                if(data.location.equals("null")){
                    itemBinding.locationTextView.text = ""
                }else{
                    itemBinding.locationTextView.text = "${data.location}"
                }

                itemBinding.timeTextView.text = data.time

                val SuccessColor = ContextCompat.getColor(this, R.color.green_800)
                val PendingColor = ContextCompat.getColor(this, R.color.amber_300)
                val CancelColor = ContextCompat.getColor(this, R.color.red_800)
                if(data.cek.equals("1")){
                    itemBinding.hasilcek.setColorFilter(SuccessColor, PorterDuff.Mode.SRC_IN)
                }else if(data.cek.equals("2")){
                    itemBinding.hasilcek.setColorFilter(CancelColor, PorterDuff.Mode.SRC_IN)
                }else{
                    itemBinding.hasilcek.setColorFilter(PendingColor, PorterDuff.Mode.SRC_IN)
                }

                binding.layoutData.addView(itemBinding.root)
            }
        }





    }



    private fun initToolbar() {
        val itemName = intent.getStringExtra("NoSPB")
        binding.toolbar.setNavigationIcon(R.drawable.ic_menu)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = itemName
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        Tools.setSystemBarColor(this)
    }



}
