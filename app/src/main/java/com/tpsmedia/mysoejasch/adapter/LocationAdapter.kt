package com.tpsmedia.mysoejasch.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.tpsmedia.mysoejasch.model.Warehouse.Location.Datum
import com.tpsmedia.mysoejasch.databinding.ItemLocationBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocationAdapter(private val dataList: MutableList<Datum>) :
    RecyclerView.Adapter<LocationAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val dataItem = dataList[position]
        holder.bind(dataItem)
    }

    override fun getItemCount() = dataList.size

    fun addData(newData: MutableList<Datum>) {
        val positionStart = itemCount
        dataList.addAll(newData)
        notifyItemRangeInserted(positionStart, newData.size)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearData() {
        dataList.clear()  // Clear all data in the list
        notifyDataSetChanged()  // Notify the adapter that the data has been cleared
    }

    class DataViewHolder(private val binding: ItemLocationBinding) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(dataItem: Datum) {

            binding.name.text = dataItem.kode_lokasi
            binding.textView6.text = dataItem.nama_lokasi

            binding.textView7.text = dataItem.totalKG.toString() + " KG"

            binding.progressBar.progress = dataItem.total?.toInt()!!

        }
    }
}
