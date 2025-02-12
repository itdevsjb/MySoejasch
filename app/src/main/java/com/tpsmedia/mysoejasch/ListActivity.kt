package com.tpsmedia.mysoejasch

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
import com.tpsmedia.appmanager.model.PostModel
import com.tpsmedia.materialx.ui.design.utils.Tools
import com.tpsmedia.mysoejasch.databinding.ActivityListBinding
import com.tpsmedia.mysoejasch.holder.ListViewHolder
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder


class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    val groupAdapter = GroupAdapter<GroupieViewHolder>()
    var post_model_array_list = ArrayList<PostModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AdsManager().initBanner(this, binding.lyBannerAds)
        initToolbar()
        initComponent()
    }

    private fun initToolbar() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_menu)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = "List Basic"
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

        binding.swipeRefresh.setOnRefreshListener {
            getData()
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
        ServerManager().getPosts(this) {
            if (it != null) {
                post_model_array_list = it
            }
            showData()
        }
    }

    private fun showData() {
        binding.swipeRefresh.isRefreshing = false
        groupAdapter.clear()
        var index = 0
//        post_model_array_list
//            .filter { ((it.post_title + " " + it.post_content).lowercase().contains(searchKeyword.lowercase())) }
//            .forEach {
//                groupAdapter.add(ListViewHolder(it, this))
//                index++
//                if ((index % 7) == 1) {
//                    groupAdapter.add(AdsViewHolder(this, 0, "home"))
//                }
//            }
    }
}