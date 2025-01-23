package com.tpsmedia.mysoejasch.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.tpsmedia.mysoejasch.model.Warehouse.StokMasuk.Datum
import com.tpsmedia.mysoejasch.databinding.ItemWmsStokmasukBinding
import com.tpsmedia.mysoejasch.pronline.PRDetailActivity
import com.tpsmedia.mysoejasch.warehouse.stokmasuk.StokMasukDetailActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class StokMasukAdapter(private val dataList: MutableList<Datum>) :
    RecyclerView.Adapter<StokMasukAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemWmsStokmasukBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    @SuppressLint("NotifyDataSetChanged")
    fun clearData() {
        dataList.clear()  // Clear all data in the list
        notifyDataSetChanged()  // Notify the adapter that the data has been cleared
    }

    class DataViewHolder(private val binding: ItemWmsStokmasukBinding) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(dataItem: Datum) {
            val fromFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val targetFormat = DateTimeFormatter.ofPattern("dd/MMM/yyyy")

            val tanggal: LocalDate = LocalDate.parse(dataItem.tgl_inbound.replace("T00:00:00.000Z",""), fromFormat)
            val formattedDate = tanggal.format(targetFormat)

            if(dataItem.keterangan == null){
                binding.txtSubtitle.text = "( No Remark )"
            }else{
                binding.txtSubtitle.text = dataItem.keterangan
            }

            binding.txtTitle.text = dataItem.no_inbound
            binding.txtTanggal.text = formattedDate
            binding.cdview.setBackgroundColor(Color.parseColor(dataItem.warna))
            binding.txtUser.text = dataItem.nama_karyawan

            binding.labeling.text = dataItem.jumlah_box + " BX"

            if (dataItem.jumlah_kg == null){
                binding.labeling2.text = "0 KG"
            }else{
                binding.labeling2.text = dataItem.jumlah_kg + " KG"
            }






            binding.cdview.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, StokMasukDetailActivity::class.java)
                intent.putExtra("no_inbound", dataItem.no_inbound)
                intent.putExtra("ucode_inbound", dataItem.ucode_inbound)
                intent.putExtra("jenis_masuk", dataItem.ucode_jenis_terima)
                context.startActivity(intent)
            }

        }
    }
}
