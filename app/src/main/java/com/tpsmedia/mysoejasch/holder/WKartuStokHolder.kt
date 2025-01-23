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
import com.tpsmedia.mysoejasch.model.Warehouse.KartuStok.Datum
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class WKartuStokHolder(private val dataList: List<Datum>) :
    RecyclerView.Adapter<WKartuStokHolder.KartuViewHolder>() {

    private var context: Context? = null

    inner class KartuViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var txtTitle: TextView = mView.findViewById(R.id.tvJenis)
        var txtTanggal: TextView = mView.findViewById(R.id.tvTanggal)
        var txtKeterangan: TextView = mView.findViewById(R.id.tvNamaBarang)
        var txtQty: TextView = mView.findViewById(R.id.tvTotal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KartuViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.item_wms_kartustokk, parent, false)

        context = parent.context
        return KartuViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n", "DefaultLocale")
    override fun onBindViewHolder(holder: KartuViewHolder, position: Int) {

        holder.txtTitle.text = dataList[position].type
        holder.txtKeterangan.text = dataList[position].keterangan
        holder.txtQty.text = String.format("%,.2f", dataList[position].qty_std.toDouble()) + " KG"

        val fromFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val targetFormat = DateTimeFormatter.ofPattern("dd/MMM/yyyy")
        val tanggal: LocalDate = LocalDate.parse(dataList[position].tanggal, fromFormat)
        val formattedDate = tanggal.format(targetFormat)

        holder.txtTanggal.text = formattedDate
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    companion object {
        const val SEPARATOR: String = ","
    }
}
