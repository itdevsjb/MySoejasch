package com.tpsmedia.mysoejasch.pronline

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.tpsmedia.materialx.ui.design.utils.Tools
import com.tpsmedia.mysoejasch.MainActivity
import com.tpsmedia.mysoejasch.R
import com.tpsmedia.mysoejasch.databinding.ActivityPronlineBinding
import com.tpsmedia.mysoejasch.holder.ListViewHolder
import com.tpsmedia.mysoejasch.model.Purchaserequest.Datum
import com.tpsmedia.mysoejasch.service.SQLiteHelper
import com.tpsmedia.mysoejasch.service.ServiceLogin
import com.tpsmedia.mysoejasch.service.SinkronasiData
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder


class PROnlineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPronlineBinding
    val groupAdapter = GroupAdapter<GroupieViewHolder>()
    var post_model_array_list = ArrayList<Datum>()
    private lateinit var dbHelper: SQLiteHelper

    private var drawerLayout: DrawerLayout? = null
    private val toggle: ActionBarDrawerToggle? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPronlineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbHelper = SQLiteHelper(this)
        val serviceLogin = ServiceLogin(this)

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

        setInterval(3000) {
            getData()
            println("This runs every second")
        }
    }

    fun setInterval(intervalMillis: Long, action: () -> Unit) {
        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                action()  // Execute the action
                handler.postDelayed(this, intervalMillis)  // Re-run this after the interval
            }
        }
        handler.post(runnable)  // Start the first execution
    }

    private fun initToolbar() {
        val serviceLogin = ServiceLogin(this)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = "PR - Need Approve"
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
            if(servicesinkron.sinkronPPB(this) == true){
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
//        binding.swipeRefresh.isRefreshing = true
        getAllData {
            if (it != null) {
                post_model_array_list = it as ArrayList<Datum>
            }
            showData()
        }
    }

    private fun getAllData(callback: (List<Datum>?) -> Unit) {
        Thread {
            val dataList = dbHelper.getAllData(this)
            println(dataList)
            runOnUiThread {
                callback(dataList)
//                binding.swipeRefresh.isRefreshing = false
                println(dataList.count())
                if (dataList.isEmpty()) {
                    println("OKE")
                    binding.swipeRefresh.visibility = View.GONE
                    binding.noPage.visibility = View.VISIBLE

                }else{
                    println("NOT OKE")
                    binding.noPage.visibility = View.GONE
                    binding.swipeRefresh.visibility = View.VISIBLE
                }
            }

        }.start()
    }

    private fun showData() {
        binding.swipeRefresh.isRefreshing = false
        groupAdapter.clear()
        var index = 0
        post_model_array_list
            .filter { ((it.noPPB + " " + it.ket).lowercase().contains(searchKeyword.lowercase())) }
            .forEach {
                groupAdapter.add(ListViewHolder(it, this))
                index++
            }
    }

    //    Menu

    fun showMain() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
    }

    fun showNeedApprove() {
        startActivity(Intent(applicationContext, PROnlineActivity::class.java))
    }

    fun showHistory(type : String) {
        val intent = Intent(this, PROnlineHistoryActivity::class.java)
        if(type.equals("pending")){
            intent.putExtra("pr-activity", "pending")
        }else if(type.equals("cancel")){
            intent.putExtra("pr-activity", "cancel")
        }else{
            intent.putExtra("pr-activity", "history")
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