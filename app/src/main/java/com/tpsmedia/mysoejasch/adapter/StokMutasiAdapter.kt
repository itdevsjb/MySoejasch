package com.tpsmedia.mysoejasch.adapter

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.tpsmedia.mysoejasch.model.Warehouse.StokMutasi.Datum
import com.tpsmedia.mysoejasch.databinding.ItemWmsStokmutasiBinding
import com.tpsmedia.mysoejasch.warehouse.stokmasuk.StokMasukDetailActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class StokMutasiAdapter(private val dataList: MutableList<Datum>) :
    RecyclerView.Adapter<StokMutasiAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemWmsStokmutasiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val dataItem = dataList[position]
        holder.bind(dataItem)
    }

    override fun getItemCount() = dataList.size

    fun addData(newData: List<Datum>) {
        val positionStart = itemCount
        dataList.addAll(newData)
        notifyItemRangeInserted(positionStart, newData.size)
    }

    class DataViewHolder(private val binding: ItemWmsStokmutasiBinding) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(dataItem: Datum) {
            val fromFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val targetFormat = DateTimeFormatter.ofPattern("dd/MMM/yyyy")

            val tanggal: LocalDate = LocalDate.parse(dataItem.tgl_mutasi.replace("T00:00:00.000Z",""), fromFormat)
            val formattedDate = tanggal.format(targetFormat)

            if(dataItem.keterangan == null){
                binding.txtSubtitle.text = "( No Remark )"
            }else{
                binding.txtSubtitle.text = dataItem.keterangan
            }

            binding.txtTitle.text = dataItem.no_mutasi
            binding.txtTanggal.text = formattedDate



            binding.cdview.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, StokMasukDetailActivity::class.java)
                intent.putExtra("no_mutasi", dataItem.no_mutasi)
                intent.putExtra("ucode_mutasi", dataItem.ucode_mutasi)
                context.startActivity(intent)
            }

        }
    }
}
