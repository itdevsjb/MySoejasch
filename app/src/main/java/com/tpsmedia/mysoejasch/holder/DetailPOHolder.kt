package com.tpsmedia.mysoejasch.holder

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.tpsmedia.mysoejasch.R
import com.tpsmedia.mysoejasch.databinding.ItemPodetailBinding
import com.tpsmedia.mysoejasch.model.Purchaseorderdetail.Datum
import com.tpsmedia.mysoejasch.service.SQLiteHelper
import com.tpsmedia.mysoejasch.service.ServiceSetData
import com.xwray.groupie.viewbinding.BindableItem
import java.text.DecimalFormat


class DetailPOHolder(var postModel: Datum, var activity: Activity) : BindableItem<ItemPodetailBinding>() {
    private lateinit var dbHelper: SQLiteHelper

    var serviceSetData = ServiceSetData(activity.applicationContext)
    var itemPOCheck: StringBuilder = StringBuilder()
    var df: DecimalFormat = DecimalFormat("#,###,###,##0.00")
    override fun getLayout(): Int = R.layout.item_podetail
    override fun initializeViewBinding(view: View): ItemPodetailBinding = ItemPodetailBinding.bind(view)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun bind(viewHolder: ItemPodetailBinding, position: Int) {
        dbHelper = SQLiteHelper(activity.applicationContext)
        viewHolder.tvCoba.text = postModel.namaBrg
        val jmb = postModel.jumlahBrgStd.toDouble();
        viewHolder.jumlah.text = jmb.toString() + ' ' + postModel.namaSatStd
        viewHolder.keterangan.text = postModel.ket
        viewHolder.harga.text = "@ " + String.format("%,.2f", postModel.harga.toDouble());
        viewHolder.subtotal.text = String.format("%,.2f", postModel.subtotal.toDouble());

        viewHolder.tvCoba.isChecked = true;
        dbHelper.setSPBCheckbox(activity.applicationContext, postModel.uCode_SPB, postModel.ucodeBrg );

        viewHolder.tvCoba.setOnClickListener(View.OnClickListener {
            val nilai: String = postModel.ucodeBrg + ";"
            if (viewHolder.tvCoba.isChecked) {
                dbHelper.setSPBCheckbox(activity.applicationContext, postModel.uCode_SPB, postModel.ucodeBrg );
            } else {
                dbHelper.removesetSPBCheckbox(activity.applicationContext, postModel.uCode_SPB, postModel.ucodeBrg);
            }
        })
    }



}

