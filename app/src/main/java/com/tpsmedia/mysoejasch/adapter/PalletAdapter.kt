package com.tpsmedia.mysoejasch.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.tpsmedia.mysoejasch.model.Warehouse.Pallet.Datum
import com.tpsmedia.mysoejasch.databinding.ItemPalletBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class PalletAdapter(private val dataList: MutableList<Datum>) :
    RecyclerView.Adapter<PalletAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemPalletBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val dataItem = dataList[position]
        holder.bind(dataItem)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearData() {
        dataList.clear()  // Clear all data in the list
        notifyDataSetChanged()  // Notify the adapter that the data has been cleared
    }

    override fun getItemCount() = dataList.size

    fun addData(newData: MutableList<Datum>) {
        val positionStart = itemCount
        dataList.addAll(newData)
        notifyItemRangeInserted(positionStart, newData.size)
    }

    class DataViewHolder(private val binding: ItemPalletBinding) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(dataItem: Datum) {

            binding.name.text = dataItem.kode_Pallet
            binding.textView6.text = dataItem.nama_Pallet

            binding.progressBar.progress = dataItem.total

            val qtyDecimal = dataItem.totalKG.toBigDecimal()
            binding.textView7.text = qtyDecimal.setScale(2, java.math.RoundingMode.HALF_EVEN).toString() + " KG"


        }
    }
}
