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
import com.tpsmedia.mysoejasch.model.Warehouse.Produksi.Datum
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class WProduksiHolder(private val dataList: List<Datum>) :
    RecyclerView.Adapter<WProduksiHolder.ProduksiViewHolder>() {


    private var context: Context? = null

    // ViewHolder internal class
    inner class ProduksiViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var txtTitle: TextView = mView.findViewById(R.id.txtTitle)
        var txtQty: TextView = mView.findViewById(R.id.labeling)
        var txtHasil: TextView = mView.findViewById(R.id.labeling2)
        var txtRSPC: TextView = mView.findViewById(R.id.txtTujuan)
        var txtRemark: TextView = mView.findViewById(R.id.txtSubtitle)
        var txtTanggal: TextView = mView.findViewById(R.id.textTanggal)

        var txtNoOP: TextView = mView.findViewById(R.id.textView5)
        var txtStatus: TextView = mView.findViewById(R.id.textView8)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProduksiViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.item_wms_produksi, parent, false)
        context = parent.context
        return ProduksiViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n", "DefaultLocale")
    override fun onBindViewHolder(holder: ProduksiViewHolder, position: Int) {
        // Menampilkan nama barang pada item
        holder.txtTitle.text = dataList[position].nama_Brg
        holder.txtRemark.text = dataList[position].remark
        holder.txtQty.text = String.format("%,.2f", dataList[position].qty.toDouble()) + " KG"
        holder.txtRSPC.text = dataList[position].no_SPC_Rekap
        holder.txtHasil.text = String.format("%,.2f", dataList[position].hasil.toDouble()) + " KG"

        val fromFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val targetFormat = DateTimeFormatter.ofPattern("dd/MMM/yyyy")
        val tanggal: LocalDate = LocalDate.parse(dataList[position].tanggalProduksi, fromFormat)
        val formattedDate = tanggal.format(targetFormat)

        holder.txtTanggal.text = formattedDate

        if (dataList[position].no_OP != null){
            holder.txtNoOP.text = dataList[position].no_OP
        }else{
            holder.txtNoOP.text = "Belum dibuat OP"
        }


    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    companion object {
        const val SEPARATOR: String = ","
    }
}
