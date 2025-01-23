package com.tpsmedia.mysoejasch.adapter

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.tpsmedia.mysoejasch.model.Warehouse.StokMutasiDetail.Datum
import com.tpsmedia.mysoejasch.databinding.ItemWmsStokmutasiDetailBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class StokMutasiDetailAdapter(private val dataList: MutableList<Datum>) :
    RecyclerView.Adapter<StokMutasiDetailAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemWmsStokmutasiDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    class DataViewHolder(private val binding: ItemWmsStokmutasiDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(dataItem: Datum) {

            binding.txtTitle.text = dataItem.nama_Brg

        }
    }
}
