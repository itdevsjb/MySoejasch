package com.tpsmedia.mysoejasch.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.tpsmedia.mysoejasch.model.CTPlanData.Datum
import com.tpsmedia.mysoejasch.databinding.ItemWmsStokkeluarCtDetailBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class StokKeluarDetailCTAdapter(private val dataList: MutableList<Datum>) :
    RecyclerView.Adapter<StokKeluarDetailCTAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemWmsStokkeluarCtDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    fun clearData() {
        dataList.clear()  // Clear the list
        notifyDataSetChanged()  // Notify the adapter that data has changed
    }

    class DataViewHolder(private val binding: ItemWmsStokkeluarCtDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        @RequiresApi(Build.VERSION_CODES.O)
        val targetFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(dataItem: Datum) {

//            val tanggal: LocalDate = LocalDate.parse(dataItem.tgl_expired.replace("T00:00:00.000Z",""), formatter)
//            val formattedDate = tanggal.format(targetFormat)
//
//
            binding.txtTitle.text = dataItem.nama_Brg
            binding.txtSubtitle.text = dataItem.batch_no
            binding.txtTanggal.text = dataItem.qty.toDouble().toString() + " - " + dataItem.qtyStd.toDouble().toString() + " KG"
            binding.txtUser.text = dataItem.total_scan.toDouble().toString() + " - " + dataItem.total_scan_std.toDouble().toString() + " KG"
            if(dataItem.sjtb == null || dataItem.sjtb.equals(null)){
                binding.textView4.text = "Waiting for submit"
            }else{
                binding.textView4.text = dataItem.sjtb
            }

            if (dataItem.total_scan.toDouble() >= dataItem.qty.toDouble()){
                binding.imageView4.visibility = View.INVISIBLE
                binding.imageView5.visibility = View.VISIBLE
            }else{
                binding.imageView4.visibility = View.VISIBLE
                binding.imageView5.visibility = View.INVISIBLE
            }

//            binding.txtTanggal.text = formattedDate
//
//            binding.txtUser.text = dataItem.kode_Pallet

        }
    }
}
