package com.tpsmedia.mysoejasch.poonline;

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tpsmedia.materialx.ui.design.utils.Tools
import com.tpsmedia.mysoejasch.api.Client
import com.tpsmedia.mysoejasch.api.Sinkronasi
import com.tpsmedia.mysoejasch.databinding.ActivityPodetailBinding
import com.tpsmedia.mysoejasch.holder.DetailPOHolder
import com.tpsmedia.mysoejasch.model.GetReponseSuccess
import com.tpsmedia.mysoejasch.model.Purchaseorderdetail.Datum
import com.tpsmedia.mysoejasch.model.Purchaseorderdetail.Purchaseorderdetail
import com.tpsmedia.mysoejasch.pronline.PROnlineActivity
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


class PODetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPodetailBinding
    private lateinit var dbHelper: SQLiteHelper

    val groupAdapter = GroupAdapter<GroupieViewHolder>()
    var post_model_array_list = ArrayList<Datum>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPodetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = SQLiteHelper(this)
        var serviceLogin = ServiceLogin(this)
        var serviceSetData = ServiceSetData(this)
        var serviceData = ServiceData(this)

        initToolbar();

        val token = serviceLogin.token;
        val NoSPB = intent.getStringExtra("NoSPB");
        val UcodeSPB = intent.getStringExtra("UcodeSPB");
        val TglPPB = intent.getStringExtra("TglSPB");
        val Ket = intent.getStringExtra("Ket");
        val Karyawan = intent.getStringExtra("Karyawan");
        val Supplier = intent.getStringExtra("Supplier");

        val jenis = intent.getStringExtra("jenis");
        val lampiran = intent.getStringExtra("lampiran");

        val approval_1 = intent.getStringExtra("approval_1");
        val approval_2 = intent.getStringExtra("approval_2");
        val approval_3 = intent.getStringExtra("approval_3");
        val approval_4 = intent.getStringExtra("approval_4");

        val cek_approval_1 = intent.getStringExtra("cek_approval_1");
        val cek_approval_2 = intent.getStringExtra("cek_approval_2");
        val cek_approval_3 = intent.getStringExtra("cek_approval_3");
        val cek_approval_4 = intent.getStringExtra("cek_approval_4");

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val targetFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        val tanggal: LocalDate = LocalDate.parse(TglPPB?.replace(" 00:00:00.000","") ?: "" , formatter)
        val formattedDate = tanggal.format(targetFormat)

        if(serviceLogin.getpolevel().equals("1")){
            binding.button2.visibility = View.INVISIBLE;
            binding.EdRemarksUser.visibility = View.INVISIBLE;
            println("invisible")
        }else if(serviceLogin.getpolevel().equals("2")){
            if(cek_approval_1 == null){
                binding.button2.visibility = View.VISIBLE;
                binding.EdRemarksUser.visibility = View.VISIBLE;
            }else{
                binding.button2.visibility = View.INVISIBLE;
                binding.EdRemarksUser.visibility = View.INVISIBLE;
            }
        }else if(serviceLogin.getpolevel().equals("3")){
            if(cek_approval_2 == null){
                binding.button2.visibility = View.VISIBLE;
                binding.EdRemarksUser.visibility = View.VISIBLE;
            }else{
                binding.button2.visibility = View.INVISIBLE;
                binding.EdRemarksUser.visibility = View.INVISIBLE;
            }
        }else if(serviceLogin.getpolevel().equals("4")){
            if(cek_approval_3 == null){
                binding.button2.visibility = View.VISIBLE;
                binding.EdRemarksUser.visibility = View.VISIBLE;
            }else{
                binding.button2.visibility = View.INVISIBLE;
                binding.EdRemarksUser.visibility = View.INVISIBLE;
            }
        }else{
            if(cek_approval_4 == null){
                binding.button2.visibility = View.VISIBLE;
                binding.EdRemarksUser.visibility = View.VISIBLE;
            }else{
                binding.button2.visibility = View.INVISIBLE;
                binding.EdRemarksUser.visibility = View.INVISIBLE;
            }
        }

        if(serviceLogin.getpolevel().equals("4")){
            if(jenis.equals("JASA")){
                binding.switch1.visibility = View.VISIBLE;
            }else{
                binding.switch1.visibility = View.GONE;
            }
        }else{
            binding.switch1.visibility = View.GONE;
        }

        if(lampiran.equals("")){
            binding.lampiran.visibility = View.GONE;
        }else{
            binding.lampiran.visibility = View.VISIBLE;
        }

        binding.EdTanggal.setText(formattedDate);
        binding.EdRequestor.setText(Karyawan);
        binding.EdRemarks.setText(Ket);
        binding.EdSupplier.setText(Supplier)
        initComponent();

        binding.lampiran.setOnClickListener(View.OnClickListener {
            val pdfurl =
                "http://114.129.18.62:1000/api-portal-soejasch/public/data_file/$lampiran"
            viewpdf(pdfurl)
        })

        binding.button2.setOnClickListener(View.OnClickListener {
            val servicesinkron = SinkronasiData(this);
            val service = Client.getClient().create(
                Sinkronasi::class.java
            )
            val allBarang = dbHelper.getBarangCheckSPB(applicationContext, UcodeSPB);
            if(jenis.equals("BARANG")){
                val call: Call<GetReponseSuccess> = service.postApproveSPB(
                    "Bearer $token",
                    serviceLogin.getpolevel(),
                    serviceLogin.loginId,
                    serviceLogin.loginName,
                    binding.spinner.selectedItem.toString(),
                    serviceData.device,
                    "0",
                    serviceData.lat,
                    serviceData.long,
                    binding.EdRemarksUser.text.toString(),
                    allBarang,
                    UcodeSPB
                )
                call.enqueue(object : Callback<GetReponseSuccess?> {
                    override fun onResponse(
                        call: Call<GetReponseSuccess?>,
                        response: Response<GetReponseSuccess?>
                    ) {
                        if (response.isSuccessful) {
                            if(servicesinkron.sinkronSPBSingle(this@PODetailActivity, UcodeSPB)){
                                Toast.makeText(
                                    applicationContext,
                                    "Berhasil, mengalihkan mohon tunggu...",
                                    Toast.LENGTH_SHORT
                                ).show()
                                setTimeout(3000) {
                                    startActivity(Intent(applicationContext, POOnlineActivity::class.java))
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
            }else{
                val isChecked = binding.switch1.isChecked
                val terus = if (isChecked) {
                    "1"
                } else {
                    "0"
                }
                val call: Call<GetReponseSuccess> = service.postApproveSPJ(
                    "Bearer $token",
                    serviceLogin.getpolevel(),
                    serviceLogin.loginId,
                    serviceLogin.loginName,
                    binding.spinner.selectedItem.toString(),
                    serviceData.device,
                    "0",
                    serviceData.lat,
                    serviceData.long,
                    binding.EdRemarksUser.text.toString(),
                    allBarang,
                    terus,
                    UcodeSPB
                )
                call.enqueue(object : Callback<GetReponseSuccess?> {
                    override fun onResponse(
                        call: Call<GetReponseSuccess?>,
                        response: Response<GetReponseSuccess?>
                    ) {
                        if (response.isSuccessful) {
                            if(servicesinkron.sinkronSPJSingle(this@PODetailActivity, UcodeSPB)){
                                Toast.makeText(
                                    applicationContext,
                                    "Berhasil, mengalihkan mohon tunggu...",
                                    Toast.LENGTH_SHORT
                                ).show()
                                setTimeout(3000) {
                                    startActivity(Intent(applicationContext, POOnlineActivity::class.java))
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
            }




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
        val itemName = intent.getStringExtra("NoSPB")
        binding.toolbar.setNavigationIcon(com.tpsmedia.mysoejasch.R.drawable.ic_menu)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = itemName
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        Tools.setSystemBarColor(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(com.tpsmedia.mysoejasch.R.menu.menu_cek, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            com.tpsmedia.mysoejasch.R.id.action_cek -> {

                val NoSPB = intent.getStringExtra("NoSPB");
                val UcodeSPB = intent.getStringExtra("UcodeSPB");

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


                val intent = Intent(this, HistoryPOActivity::class.java)
                intent.putExtra("NoSPB", NoSPB)
                intent.putExtra("UcodeSPB", UcodeSPB)

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
        val UcodeSPB = intent.getStringExtra("UcodeSPB");
        val call = service.postpoonlinedetail("Bearer " + serviceLogin.token, UcodeSPB )
        call.enqueue(object : Callback<Purchaseorderdetail> {
            override fun onResponse(
                call: Call<Purchaseorderdetail>,
                response: Response<Purchaseorderdetail>
            ) {
                if (response.body()!!.data != null) {
                    val total = response.body()!!.data.count()
                    var cek = 0 // Initialize cek outside the loop to keep track of successful inserts

                    val db = dbHelper.writableDatabase // Open the database once before the loop

                    for (data in response.body()!!.data) {
                        try {
                            db.execSQL(
                                "INSERT OR REPLACE INTO tb_dt_SPB_brg (UCode_SPB, No_Urut, UCode_Brg, Kode_Brg, Nama_Brg, Qty, Satuan, Qty_Std, Satuan_Std, Ket, Approval_cek, Harga, Discount, Sub_Total, Pajak) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                                arrayOf<Any>(
                                    data.uCode_SPB,
                                    data.no_Urut,
                                    data.ucodeBrg,
                                    data.kodeBrg,
                                    data.namaBrg,
                                    data.jumlahBrg,
                                    data.namaSat,
                                    data.jumlahBrgStd,
                                    data.namaSatStd,
                                    data.ket ?: "",
                                    data.approveBrg,
                                    data.harga,
                                    data.discount,
                                    data.subtotal,
                                    data.pajak
                                )
                            )
                            cek += 1
                        } catch (e: Exception) {
                            Log.e("DatabaseError", "Failed to insert data: ${e.message}")
                        }
                    }

                    if (cek == total) {
                        setTimeout(1000) {
                            getData()
                        }
                        setTimeout(2000) {
                            getTotal()
                        }

                    } else {
                        Toast.makeText(this@PODetailActivity, "Some records failed to insert", Toast.LENGTH_SHORT).show()
                    }

                    db.close() // Close the database after the loop
                } else {
                    Toast.makeText(this@PODetailActivity, "Data Tidak Ada", Toast.LENGTH_SHORT).show()
                }

            }
            override fun onFailure(call: Call<Purchaseorderdetail>, t: Throwable) {
                Toast.makeText(
                    this@PODetailActivity,
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

    @SuppressLint("DefaultLocale")
    private fun getTotal(){
        val UcodeSPB = intent.getStringExtra("UcodeSPB");
        val subtotal = dbHelper.getSubTotal(this, UcodeSPB).toDouble();
        val pajak = dbHelper.getSubPajak(this, UcodeSPB).toDouble();
        binding.textView5a.text =  String.format("%,.2f", subtotal );
        binding.textView5b.text =  String.format("%,.2f", pajak);
        binding.textView5c.text =  String.format("%,.2f", (subtotal + pajak));
    }


    private fun getAllData(callback: (List<Datum>?) -> Unit) {
        Thread {
            val dataList = dbHelper.getAllDetailSPB()
            runOnUiThread {
                callback(dataList)
            }
        }.start()
    }

    private fun showData() {
        groupAdapter.clear()
        var index = 0
        val UcodeSPB = intent.getStringExtra("UcodeSPB");
        post_model_array_list
            .filter { it.uCode_SPB == UcodeSPB }
            .forEach {
                groupAdapter.add(DetailPOHolder(it, this))
                index++

            }
    }


    private fun viewpdf(url: String) {
        val value = url
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(value))
        startActivity(intent)
    }

}
