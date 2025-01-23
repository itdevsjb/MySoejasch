package com.tpsmedia.appmanager

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.tpsmedia.appmanager.model.PROnlineModel
import com.tpsmedia.appmanager.model.PostModel
import com.tpsmedia.appmanager.model.PostResponse
import java.io.InputStream

class LocalManager {
    fun getPosts(context: Context): ArrayList<PostModel> {
        val list: Array<String>? = context.assets.list("json")
        if (list.isNullOrEmpty()) {
            return ArrayList()
        } else {
            val gson = Gson()
            val postModelArrayList = ArrayList<PostModel>()
            for (file in list) {
                var json: String?
                try {
                    val inputStream: InputStream = context.assets.open("json/$file")
                    json = inputStream.bufferedReader().use { it.readText() }
                    val postResponse = gson.fromJson(json, PostResponse::class.java)
                    postResponse?.datareal?.forEach {
                        postModelArrayList.add(it)
                    }
                } catch (ex: Exception) {
                    Log.d("LOG", "Failed read  : $file")
                    ex.printStackTrace()
                }
            }
            Log.d("LOG", "getPosts Local:  " + postModelArrayList.size)
            return postModelArrayList
        }
    }

    fun getPrOnlines(context: Context): ArrayList<PROnlineModel> {
        val list: Array<String>? = context.assets.list("json")
        if (list.isNullOrEmpty()) {
            return ArrayList()
        } else {
            val gson = Gson()
            val postModelArrayList = ArrayList<PROnlineModel>()
            for (file in list) {
                var json: String?
                try {
                    val inputStream: InputStream = context.assets.open("json/$file")
                    json = inputStream.bufferedReader().use { it.readText() }
                    val postResponse = gson.fromJson(json, PostResponse::class.java)
                    postResponse?.data?.forEach {
                        postModelArrayList.add(it)
                    }
                } catch (ex: Exception) {
                    Log.d("LOG", "Failed read  : $file")
                    ex.printStackTrace()
                }
            }
            Log.d("LOG", "getPosts Local:  " + postModelArrayList.size)
            return postModelArrayList
        }
    }
}