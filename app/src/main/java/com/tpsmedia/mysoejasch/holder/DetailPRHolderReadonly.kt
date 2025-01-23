package com.tpsmedia.mysoejasch.holder

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.tpsmedia.materialx.ui.design.utils.Tools
import com.tpsmedia.mysoejasch.R
import com.tpsmedia.mysoejasch.databinding.ItemPrdetailReadonlyBinding
import com.tpsmedia.mysoejasch.model.Purchaserequestdetail.Datum
import com.tpsmedia.mysoejasch.poonline.PODetailActivity
import com.xwray.groupie.viewbinding.BindableItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DetailPRHolderReadonly(var postModel: Datum, var activity: Activity) : BindableItem<ItemPrdetailReadonlyBinding>() {

    override fun getLayout(): Int = R.layout.item_prdetail_readonly
    override fun initializeViewBinding(view: View): ItemPrdetailReadonlyBinding = ItemPrdetailReadonlyBinding.bind(view)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun bind(viewHolder: ItemPrdetailReadonlyBinding, position: Int) {
        viewHolder.tvCoba.text = postModel.namaBrg
        val jmb = postModel.jumlahBrgStd.toDouble();
        viewHolder.jumlah.text = jmb.toString() + ' ' + postModel.namaSatStd
        viewHolder.keterangan.text = postModel.ket
    }


}

