package com.tpsmedia.mysoejasch.holder

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.tpsmedia.materialx.ui.design.utils.Tools
import com.tpsmedia.mysoejasch.R
import com.tpsmedia.mysoejasch.databinding.ItemPrdetailBinding
import com.tpsmedia.mysoejasch.model.Purchaserequestdetail.Datum
import com.tpsmedia.mysoejasch.poonline.PODetailActivity
import com.tpsmedia.mysoejasch.service.SQLiteHelper
import com.xwray.groupie.viewbinding.BindableItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DetailPRHolder(var postModel: Datum, var activity: Activity) : BindableItem<ItemPrdetailBinding>() {

//    @RequiresApi(Build.VERSION_CODES.O)
//    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
//    @RequiresApi(Build.VERSION_CODES.O)
//    val targetFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    private lateinit var dbHelper: SQLiteHelper
    override fun getLayout(): Int = R.layout.item_prdetail
    override fun initializeViewBinding(view: View): ItemPrdetailBinding = ItemPrdetailBinding.bind(view)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun bind(viewHolder: ItemPrdetailBinding, position: Int) {
        dbHelper = SQLiteHelper(activity.applicationContext)
        viewHolder.tvCoba.text = postModel.namaBrg
        val jmb = postModel.jumlahBrgStd.toDouble();
        viewHolder.jumlah.text = jmb.toString() + ' ' + postModel.namaSatStd
        viewHolder.keterangan.text = postModel.ket

        viewHolder.tvCoba.isChecked = true;
        dbHelper.setPPBCheckbox(activity.applicationContext, postModel.uCodePPB, postModel.ucodeBrg );

        viewHolder.tvCoba.setOnClickListener(View.OnClickListener {
            val nilai: String = postModel.ucodeBrg + ";"
            if (viewHolder.tvCoba.isChecked) {
                dbHelper.setPPBCheckbox(activity.applicationContext, postModel.uCodePPB, postModel.ucodeBrg );
            } else {
                dbHelper.removesetPPBCheckbox(activity.applicationContext, postModel.uCodePPB, postModel.ucodeBrg);
            }
        })

    }
}

