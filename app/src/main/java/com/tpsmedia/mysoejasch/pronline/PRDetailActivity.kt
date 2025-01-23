package com.tpsmedia.mysoejasch.pronline;

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.core.Context
import com.tpsmedia.materialx.ui.design.utils.Tools
import com.tpsmedia.mysoejasch.R
import com.tpsmedia.mysoejasch.api.Client
import com.tpsmedia.mysoejasch.api.Sinkronasi
import com.tpsmedia.mysoejasch.databinding.ActivityPrdetailBinding
import com.tpsmedia.mysoejasch.holder.DetailPRHolder
import com.tpsmedia.mysoejasch.holder.DetailPRHolderReadonly
import com.tpsmedia.mysoejasch.model.GetReponseSuccess
import com.tpsmedia.mysoejasch.model.Purchaserequest.Purchaserequest
import com.tpsmedia.mysoejasch.model.Purchaserequestdetail.Datum
import com.tpsmedia.mysoejasch.model.Purchaserequestdetail.Purchaserequestdetail
import com.tpsmedia.mysoejasch.poonline.POOnlineActivity
import com.tpsmedia.mysoejasch.service.SQLiteHelper
import com.tpsmedia.mysoejasch.service.ServiceData
import com.tpsmedia.mysoejasch.service.ServiceLogin
import com.tpsmedia.mysoejasch.service.ServiceSetData
import com.tpsmedia.mysoejasch.service.SinkronasiData
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class PRDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPrdetailBinding
    private lateinit var dbHelper: SQLiteHelper

    val groupAdapter = GroupAdapter<GroupieViewHolder>()
    var post_model_array_list = ArrayList<Datum>()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrdetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = SQLiteHelper(this)
        var serviceLogin = ServiceLogin(this)
        var serviceSetData = ServiceSetData(this)
        var serviceData = ServiceData(this)

        initToolbar();

        val token = serviceLogin.token;
        val NoPPB = intent.getStringExtra("NoPPB");
        val UcodePPB = intent.getStringExtra("UcodePPB");
        val TglPPB = intent.getStringExtra("TglPPB");
        val Ket = intent.getStringExtra("Ket");
        val Karyawan = intent.getStringExtra("Karyawan");
        val Department = intent.getStringExtra("Department");

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val targetFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        val tanggal: LocalDate = LocalDate.parse(TglPPB?.replace(" 00:00:00.000","") ?: "" , formatter)
        val formattedDate = tanggal.format(targetFormat)


        val approval_1 = intent.getStringExtra("approval_1");
        val approval_2 = intent.getStringExtra("approval_2");
        val approval_3 = intent.getStringExtra("approval_3");
        val approval_4 = intent.getStringExtra("approval_4");

        val cek_approval_1 = intent.getStringExtra("cek_approval_1");
        val cek_approval_2 = intent.getStringExtra("cek_approval_2");
        val cek_approval_3 = intent.getStringExtra("cek_approval_3");
        val cek_approval_4 = intent.getStringExtra("cek_approval_4");

        binding.EdDepart.setText(Department);
        binding.EdTanggal.setText(formattedDate);
        binding.EdRequestor.setText(Karyawan);
        binding.EdRemarks.setText(Ket);
        binding.EdRemarks.isFocusable = false;
        binding.EdRemarks.isClickable = false;

        if(serviceLogin.getprlevel().equals("1")){
            binding.button2.visibility = View.INVISIBLE;
            binding.remarksuer.visibility = View.INVISIBLE;
            println("invisible")
        }else{

            if(serviceLogin.getprlevel().equals("5")){
                if(cek_approval_2.equals("1") && cek_approval_3 == null){
                    binding.button2.visibility = View.VISIBLE;
                    println("visible")
                }else{
                    binding.button2.visibility = View.INVISIBLE;
                    println("visible")
                }
            }else{
                println(cek_approval_2)
                if(cek_approval_1.equals("1") && cek_approval_2.equals(null)){
                    binding.button2.visibility = View.VISIBLE;
                    println("visible")
                }else{
                    binding.button2.visibility = View.INVISIBLE;
                    println("visible")
                }
            }

        }



        initComponent();


        binding.button2.setOnClickListener(View.OnClickListener {
            val servicesinkron = SinkronasiData(this);
            val service = Client.getClient().create(
                Sinkronasi::class.java
            )
            val allBarang = dbHelper.getBarangCheckPPB(applicationContext, UcodePPB);
            val call: Call<GetReponseSuccess> = service.postApprovePPB(
                "Bearer $token",
                serviceLogin.getprlevel(),
                serviceLogin.loginId,
                serviceLogin.loginName,
                "1",
                serviceData.device,
                "0",
                serviceData.lat,
                serviceData.long,
                binding.EdRemarksUser.text.toString(),
                allBarang,
                UcodePPB
            )
            call.enqueue(object : Callback<GetReponseSuccess?> {
                 override fun onResponse(
                    call: Call<GetReponseSuccess?>,
                    response: Response<GetReponseSuccess?>
                ) {
                    if (response.isSuccessful) {
                        if(servicesinkron.sinkronPPBSingle(this@PRDetailActivity, UcodePPB)){
                            Toast.makeText(
                                applicationContext,
                                "Berhasil, mengalihkan mohon tunggu...",
                                Toast.LENGTH_SHORT
                            ).show()
                            setTimeout(3000) {
                                startActivity(Intent(applicationContext, PROnlineActivity::class.java))
                            }
                        }
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



        })

    }

    private fun initComponent() {
        val listLayoutManager = LinearLayoutManager(this)
        listLayoutManager.orientation = RecyclerView.VERTICAL
        listLayoutManager.generateDefaultLayoutParams()
        val dividerItemDecoration = DividerItemDecoration(binding.recyclerView.context, listLayoutManager.orientation)
        binding.recyclerView.addItemDecoration(dividerItemDecoration)
        binding.recyclerView.layoutManager = listLayoutManager
        binding.recyclerView.adapter = groupAdapter
        downloadData()

    }

    fun setTimeout(delayMillis: Long, action: () -> Unit) {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(action, delayMillis)
    }



    private fun initToolbar() {
        val itemName = intent.getStringExtra("NoPPB")
        binding.toolbar.setNavigationIcon(com.tpsmedia.mysoejasch.R.drawable.ic_menu)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = itemName
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        Tools.setSystemBarColor(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_cek, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_cek -> {

                val NoPPB = intent.getStringExtra("NoPPB");
                val UcodePPB = intent.getStringExtra("UcodePPB");

                val approval_1 = intent.getStringExtra("approval_1");
                val approval_2 = intent.getStringExtra("approval_2");
                val approval_3 = intent.getStringExtra("approval_3");
                val approval_4 = intent.getStringExtra("approval_4");
                val no_approval_1 = intent.getStringExtra("no_approval_1");
                val no_approval_2 = intent.getStringExtra("no_approval_2");
                val no_approval_3 = intent.getStringExtra("no_approval_3");
                val no_approval_4 = intent.getStringExtra("no_approval_4");
                val cek_approval_1 = intent.getStringExtra("cek_approval_1");
                val cek_approval_2 = intent.getStringExtra("cek_approval_2");
                val cek_approval_3 = intent.getStringExtra("cek_approval_3");
                val cek_approval_4 = intent.getStringExtra("cek_approval_4");


                val intent = Intent(this, HistoryPRActivity::class.java)
                intent.putExtra("NoPPB", NoPPB)
                intent.putExtra("UcodePPB", UcodePPB)

                intent.putExtra("approval_1", approval_1)
                intent.putExtra("approval_2", approval_2)
                intent.putExtra("approval_3", approval_3)
                intent.putExtra("approval_4", approval_4)

                intent.putExtra("no_approval_1", no_approval_1)
                intent.putExtra("no_approval_2", no_approval_2)
                intent.putExtra("no_approval_3", no_approval_3)
                intent.putExtra("no_approval_4", no_approval_4)

                intent.putExtra("cek_approval_1", cek_approval_1)
                intent.putExtra("cek_approval_2", cek_approval_2)
                intent.putExtra("cek_approval_3", cek_approval_3)
                intent.putExtra("cek_approval_4", cek_approval_4)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun downloadData(){
        val serviceLogin = ServiceLogin(this)
        val service = Client.getClient().create(
            Sinkronasi::class.java
        )
        val UcodePPB = intent.getStringExtra("UcodePPB");
        val call = service.postpronlinedetail("Bearer " + serviceLogin.token, UcodePPB )
        call.enqueue(object : Callback<Purchaserequestdetail> {
            override fun onResponse(
                call: Call<Purchaserequestdetail>,
                response: Response<Purchaserequestdetail>
            ) {
                if (response.body()!!.data != null) {
                    for (data in response.body()!!.data) {
                        val db = dbHelper.writableDatabase
                        db.execSQL(
                            "INSERT OR REPLACE INTO tb_dt_PPB_brg (UCode_PPB, No_Urut, UCode_Brg, Kode_Brg, Nama_Brg, Qty, Satuan, Qty_Std, Satuan_Std, Ket, Approval_cek) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                            arrayOf<Any>(
                                data.uCodePPB,
                                data.no_Urut,
                                data.ucodeBrg,
                                data.kodeBrg,
                                data.namaBrg,
                                data.jumlahBrg,
                                data.namaSat,
                                data.jumlahBrgStd,
                                data.namaSatStd,
                                data.ket ?: "",
                                data.approveBrg
                            )
                        )
                    }
                    setTimeout(2000) {
                        getData()
                    }

                } else {
                    Toast.makeText(this@PRDetailActivity, "Data Tidak Ada", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Purchaserequestdetail>, t: Throwable) {
                Toast.makeText(
                    this@PRDetailActivity,
                    "Something went wrong...Please try later!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

    }

    private fun getData() {
        getAllData {
            if (it != null) {
                post_model_array_list = it as ArrayList<Datum>
            }
            showData()
        }
    }


    private fun getAllData(callback: (List<Datum>?) -> Unit) {
        Thread {
            val dataList = dbHelper.getAllDetailPPB()
            runOnUiThread {
                callback(dataList)
            }
        }.start()
    }

    private fun showData() {
        var serviceLogin = ServiceLogin(this)
        groupAdapter.clear()
        var index = 0

        val NoPPB = intent.getStringExtra("NoPPB");
        val UcodePPB = intent.getStringExtra("UcodePPB");

        val approval_1 = intent.getStringExtra("approval_1");
        val approval_2 = intent.getStringExtra("approval_2");
        val approval_3 = intent.getStringExtra("approval_3");
        val approval_4 = intent.getStringExtra("approval_4");
        val no_approval_1 = intent.getStringExtra("no_approval_1");
        val no_approval_2 = intent.getStringExtra("no_approval_2");
        val no_approval_3 = intent.getStringExtra("no_approval_3");
        val no_approval_4 = intent.getStringExtra("no_approval_4");
        val cek_approval_1 = intent.getStringExtra("cek_approval_1");
        val cek_approval_2 = intent.getStringExtra("cek_approval_2");
        val cek_approval_3 = intent.getStringExtra("cek_approval_3");
        val cek_approval_4 = intent.getStringExtra("cek_approval_4");

        if(serviceLogin.getprlevel().equals("1")){
            post_model_array_list
                .filter { it.uCodePPB == UcodePPB }
                .forEach {
                    groupAdapter.add(DetailPRHolderReadonly(it, this))
                    index++

                }
        }else{
            if(serviceLogin.getprlevel().equals("5")){
                if(cek_approval_3 != null){
                    post_model_array_list
                        .filter { it.uCodePPB == UcodePPB }
                        .forEach {
                            groupAdapter.add(DetailPRHolderReadonly(it, this))
                            index++
                        }
                }else{
                    post_model_array_list
                        .filter { it.uCodePPB == UcodePPB }
                        .forEach {
                            groupAdapter.add(DetailPRHolder(it, this))
                            index++
                        }
                }
            }else{
                if(cek_approval_2 != null){
                    post_model_array_list
                        .filter { it.uCodePPB == UcodePPB }
                        .forEach {
                            groupAdapter.add(DetailPRHolderReadonly(it, this))
                            index++
                        }
                }else{
                    post_model_array_list
                        .filter { it.uCodePPB == UcodePPB }
                        .forEach {
                            groupAdapter.add(DetailPRHolder(it, this))
                            index++
                        }
                }
            }

        }



    }



}
