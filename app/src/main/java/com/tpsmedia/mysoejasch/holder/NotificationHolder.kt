package com.tpsmedia.mysoejasch.holder

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.tpsmedia.appmanager.PostManager
import com.tpsmedia.appmanager.model.PROnlineModel
import com.tpsmedia.appmanager.model.PostModel
import com.tpsmedia.materialx.ui.design.utils.Tools
import com.tpsmedia.mysoejasch.R
import com.tpsmedia.mysoejasch.databinding.ItemListViewsBinding
import com.tpsmedia.mysoejasch.model.Purchaseorder.Datum
import com.tpsmedia.mysoejasch.poonline.PODetailActivity
import com.tpsmedia.mysoejasch.pronline.PRDetailActivity
import com.xwray.groupie.viewbinding.BindableItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class NotificationHolder(var postModel: Datum, var activity: Activity) : BindableItem<ItemListViewsBinding>() {
    @RequiresApi(Build.VERSION_CODES.O)
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    @RequiresApi(Build.VERSION_CODES.O)
    val targetFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    override fun getLayout(): Int = R.layout.item_list_views
    override fun initializeViewBinding(view: View): ItemListViewsBinding = ItemListViewsBinding.bind(view)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun bind(viewHolder: ItemListViewsBinding, position: Int) {
        viewHolder.txtTitle.text = postModel.noSPB
        viewHolder.txtSubtitle.text = postModel.ket
        val tanggal: LocalDate = LocalDate.parse(postModel.tglSPB.replace(" 00:00:00.000",""), formatter)
        val formattedDate = tanggal.format(targetFormat)
        viewHolder.txtDate.text = formattedDate.toString()
        Tools.displayImageRound(activity, viewHolder.imageView3, "http://114.129.18.62:1000/assets/images/users/" + postModel.foto)

        viewHolder.cdview.setOnClickListener {
            val intent = Intent(activity, PODetailActivity::class.java)
            intent.putExtra("NoSPB", postModel.noSPB)
            intent.putExtra("NoSPB", postModel.noSPB)
            intent.putExtra("NoSPB", postModel.noSPB)
            intent.putExtra("NoSPB", postModel.noSPB)
            intent.putExtra("NoSPB", postModel.noSPB)
            activity.startActivity(intent)
        }

    }
}
