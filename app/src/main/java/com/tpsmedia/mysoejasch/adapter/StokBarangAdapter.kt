package com.tpsmedia.mysoejasch.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.tpsmedia.mysoejasch.databinding.ItemWmsBarangBinding
import com.tpsmedia.mysoejasch.model.Warehouse.StokBarang.Datum
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class StokBarangAdapter(private val dataList: MutableList<Datum>) :
    RecyclerView.Adapter<StokBarangAdapter.DataViewHolder>() {

    

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemWmsBarangBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    class DataViewHolder(private val binding: ItemWmsBarangBinding) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        @RequiresApi(Build.VERSION_CODES.O)
        val targetFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(dataItem: Datum) {

            val tanggal: LocalDate = LocalDate.parse(dataItem.tgl_Expired.replace("T00:00:00.000Z",""), formatter)
            val formattedDate = tanggal.format(targetFormat)

            binding.txtTitle.text = dataItem.nama_Brg
            binding.txtSubtitle.text = dataItem.batch_No
            binding.txtTanggal.text = formattedDate

            binding.txtUser.text = dataItem.kode_Pallet

            binding.labeling.text = dataItem.karton + " BX"




            val qtyDecimal = dataItem.totalKG.toBigDecimal()
            binding.labeling2.text = qtyDecimal.setScale(2, java.math.RoundingMode.HALF_EVEN).toString() + " KG"

        }
    }
}
