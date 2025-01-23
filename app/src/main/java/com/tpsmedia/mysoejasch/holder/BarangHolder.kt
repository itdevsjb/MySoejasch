package com.tpsmedia.soejaschapp.Warehouse

import android.annotation.SuppressLint
import com.tpsmedia.mysoejasch.R
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.tpsmedia.mysoejasch.model.Warehouse.StokBarang.Datum
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class BarangHolder(private val dataList: List<Datum>) :
    RecyclerView.Adapter<BarangHolder.BarangViewHolder>() {

    // Menghindari penggunaan context nullable
    private var context: Context? = null

    // ViewHolder internal class
    inner class BarangViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var txtBarang: TextView = mView.findViewById(R.id.txtTitle)
        var txtBatch: TextView = mView.findViewById(R.id.txtSubtitle)
        var txtTglExp: TextView = mView.findViewById(R.id.txtTanggal)
        var txtBox: TextView = mView.findViewById(R.id.labeling)
        var txtKilo: TextView = mView.findViewById(R.id.labeling2)
        var txtPallet: TextView = mView.findViewById(R.id.txtUser)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.item_wms_barang, parent, false)
        context = parent.context
        return BarangViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n", "DefaultLocale")
    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {

        holder.txtBarang.text = dataList[position].nama_barang
        holder.txtBatch.text = dataList[position].batch_No
        holder.txtBox.text = String.format("%,.2f", dataList[position].karton.toDouble()) + " BX"
        holder.txtKilo.text = String.format("%,.2f", dataList[position].stock_std.toDouble()) + " KG"

        holder.txtPallet.text = dataList[position].satuan

        val fromFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val targetFormat = DateTimeFormatter.ofPattern("dd/MMM/yyyy")
        val tanggal: LocalDate = LocalDate.parse(dataList[position].tanggal_Exp, fromFormat)
        val formattedDate = tanggal.format(targetFormat)

        holder.txtTglExp.text = formattedDate
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    companion object {
        const val SEPARATOR: String = ","
    }
}
