package com.tpsmedia.mysoejasch.holder

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.squareup.picasso.Picasso
import com.tpsmedia.materialx.ui.design.utils.Tools
import com.tpsmedia.mysoejasch.R
import com.tpsmedia.mysoejasch.databinding.ItemPalletBinding
import com.tpsmedia.mysoejasch.model.Warehouse.Pallet.Datum
import com.tpsmedia.mysoejasch.poonline.PODetailActivity
import com.tpsmedia.mysoejasch.service.BluetoothPrinterHelper
import com.xwray.groupie.viewbinding.BindableItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class PalletHolder(var postModel: Datum, var activity: Activity) : BindableItem<ItemPalletBinding>() {

    private lateinit var bluetoothPrinterHelper: BluetoothPrinterHelper


    override fun getLayout(): Int = R.layout.item_pallet


    override fun initializeViewBinding(view: View): ItemPalletBinding = ItemPalletBinding.bind(view)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun bind(viewHolder: ItemPalletBinding, position: Int) {
        viewHolder.name.text = postModel.kode_Pallet

        viewHolder.progressBar.progress = postModel.total

        viewHolder.btExpand.setOnClickListener {

            bluetoothPrinterHelper = BluetoothPrinterHelper(activity)

            val nama = postModel.nama_Pallet
            val kode = postModel.kode_Pallet
            val tanggal = postModel.tgl_Jam_Buat
//            val qrCodeData = bluetoothPrinterHelper.generateQRCode(qrContent, 200)
            bluetoothPrinterHelper.printQRCodeToThermalPrinter(kode, nama, tanggal)

        }

    }
}

