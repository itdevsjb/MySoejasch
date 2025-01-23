package com.tpsmedia.mysoejasch.employee

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tpsmedia.appmanager.AdsManager
import com.tpsmedia.appmanager.ServerManager
import com.tpsmedia.appmanager.holder.AdsViewHolder
import com.tpsmedia.appmanager.model.PROnlineModel
import com.tpsmedia.appmanager.model.PostModel
import com.tpsmedia.materialx.ui.design.utils.Tools
import com.tpsmedia.mysoejasch.R
import com.tpsmedia.mysoejasch.databinding.ActivityEmployeeBinding
import com.tpsmedia.mysoejasch.holder.EmployeeHolder
import com.tpsmedia.mysoejasch.holder.ListViewHolder
import com.tpsmedia.mysoejasch.model.Employee.Datum
import com.tpsmedia.mysoejasch.model.Employee.EmployeeList
import com.tpsmedia.mysoejasch.service.SQLiteHelper
import com.tpsmedia.mysoejasch.service.SinkronasiData
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder


class EmployeeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEmployeeBinding
    val groupAdapter = GroupAdapter<GroupieViewHolder>()
    var post_model_array_list = ArrayList<Datum>()
    private lateinit var dbHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbHelper = SQLiteHelper(this)

        //AdsManager().initBanner(this, binding.lyBannerAds)
        initToolbar()
        initComponent()
    }

    private fun initToolbar() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_menu)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = "Employee List"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
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
            if(servicesinkron.sinkronEmployee(this) == true){
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
                post_model_array_list = it as ArrayList<Datum>
            }
            showData()
        }
    }

    private fun getAllData(callback: (List<Datum>?) -> Unit) {
        Thread {
            val dataList = dbHelper.getAllEmployee(this)
            runOnUiThread {
                callback(dataList)
                binding.swipeRefresh.isRefreshing = false
            }
        }.start()
    }

    private fun showData() {
        binding.swipeRefresh.isRefreshing = false
        groupAdapter.clear()
        var index = 0
        post_model_array_list
            .filter { ((it.namakaryawan + " " + it.ucodekaryawan).lowercase().contains(searchKeyword.lowercase())) }
            .forEach {
                groupAdapter.add(EmployeeHolder(it, this))
                index++
            }
    }
}