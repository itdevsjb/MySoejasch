package com.tpsmedia.mysoejasch.poonline

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.tpsmedia.appmanager.AdsManager
import com.tpsmedia.appmanager.ServerManager
import com.tpsmedia.appmanager.holder.AdsViewHolder
import com.tpsmedia.appmanager.model.PROnlineModel
import com.tpsmedia.appmanager.model.PostModel
import com.tpsmedia.materialx.ui.design.utils.Tools
import com.tpsmedia.mysoejasch.MainActivity
import com.tpsmedia.mysoejasch.R
import com.tpsmedia.mysoejasch.databinding.ActivityPoonlineBinding
import com.tpsmedia.mysoejasch.holder.ListViewHolder
import com.tpsmedia.mysoejasch.holder.POOnlineHolder
import com.tpsmedia.mysoejasch.model.Purchaserequest.Datum
import com.tpsmedia.mysoejasch.pronline.PROnlineHistoryActivity
import com.tpsmedia.mysoejasch.service.SQLiteHelper
import com.tpsmedia.mysoejasch.service.ServiceLogin
import com.tpsmedia.mysoejasch.service.SinkronasiData
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder


class POOnlineHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPoonlineBinding
    val groupAdapter = GroupAdapter<GroupieViewHolder>()
    var post_model_array_list = ArrayList<com.tpsmedia.mysoejasch.model.Purchaseorder.Datum>()
    private lateinit var dbHelper: SQLiteHelper

    private var drawerLayout: DrawerLayout? = null
    private val toggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPoonlineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbHelper = SQLiteHelper(this)

//        AdsManager().initBanner(this, binding.lyBannerAds)
        initToolbar()
        initComponent()

        drawerLayout = binding.drawerLayout
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.nav_need -> { showNeedApprove()}
                R.id.nav_cancel -> { showHistory("cancel") }
                R.id.nav_history -> { showHistory("history") }
                R.id.nav_home -> { showMain() }
            }
            drawerLayout!!.closeDrawers()
            true
        }
    }

    private fun initToolbar() {
        val serviceLogin = ServiceLogin(this)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = "PO - History Approve"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationIcon(R.drawable.ic_menu)
        binding.toolbar.setNavigationOnClickListener { v ->
            if (drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
                drawerLayout!!.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout!!.openDrawer(GravityCompat.START)
            }
        }
        Tools.setSystemBarColor(this)
    }

    private fun initComponent() {
        val listLayoutManager = LinearLayoutManager(this)
        listLayoutManager.orientation = RecyclerView.VERTICAL
        listLayoutManager.generateDefaultLayoutParams()
        val dividerItemDecoration = DividerItemDecoration(binding.recyclerView.context, listLayoutManager.orientation)
        binding.recyclerView.addItemDecoration(dividerItemDecoration)
        binding.recyclerView.layoutManager = listLayoutManager
        binding.recyclerView.adapter = groupAdapter
        getData()

        val servicesinkron = SinkronasiData(this);
        binding.swipeRefresh.setOnRefreshListener {
            if(servicesinkron.sinkronSPB(this) == true){
                getData()
            }
        }
    }

    var searchKeyword = ""
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val item = menu.findItem(R.id.action_search)
        val sv = item.actionView as SearchView
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                searchKeyword = newText
                showData()
                return true
            }
        })
        return true
    }

    private fun getData() {
        binding.swipeRefresh.isRefreshing = true
        getAllData {
            if (it != null) {
                post_model_array_list = it as ArrayList<com.tpsmedia.mysoejasch.model.Purchaseorder.Datum>
            }
            showData()
        }
    }

    private fun getAllData(callback: (List<com.tpsmedia.mysoejasch.model.Purchaseorder.Datum>?) -> Unit) {
        // Lakukan pengambilan data di thread terpisah agar tidak memblok UI
        val type = intent.getStringExtra("po-activity");
        Thread {
            val dataList = dbHelper.getAllDataSPBHistory(this, type)
            runOnUiThread {
                callback(dataList)  // Kembalikan data melalui callback di UI thread
                binding.swipeRefresh.isRefreshing = false  // Sembunyikan animasi refresh
            }
        }.start()
    }

    private fun showData() {
        binding.swipeRefresh.isRefreshing = false
        groupAdapter.clear()
        var index = 0
        post_model_array_list
            .filter { ((it.noSPB + " " + it.ket).lowercase().contains(searchKeyword.lowercase())) }
            .forEach {
                groupAdapter.add(POOnlineHolder(it, this))
                index++
                if ((index % 7) == 1) {
                    groupAdapter.add(AdsViewHolder(this, 0, "home"))
                }
            }
    }



    fun showMain() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
    }

    fun showNeedApprove() {
        startActivity(Intent(applicationContext, POOnlineActivity::class.java))
    }

    fun showHistory(type : String) {
        val intent = Intent(this, POOnlineHistoryActivity::class.java)
        if(type.equals("pending")){
            intent.putExtra("po-activity", "pending")
        }else if(type.equals("cancel")){
            intent.putExtra("po-activity", "cancel")
        }else{
            intent.putExtra("po-activity", "history")
        }
        startActivity(intent)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }


}