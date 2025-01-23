package com.tpsmedia.mysoejasch.holder

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.squareup.picasso.Picasso
import com.tpsmedia.materialx.ui.design.utils.Tools
import com.tpsmedia.mysoejasch.R
import com.tpsmedia.mysoejasch.databinding.ItemEmployeeBinding
import com.tpsmedia.mysoejasch.model.Employee.Datum
import com.xwray.groupie.viewbinding.BindableItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class WarehouseHolder(var postModel: Datum, var activity: Activity) : BindableItem<ItemEmployeeBinding>() {


    override fun getLayout(): Int = R.layout.item_employee
    override fun initializeViewBinding(view: View): ItemEmployeeBinding = ItemEmployeeBinding.bind(view)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun bind(viewHolder: ItemEmployeeBinding, position: Int) {
        viewHolder.name.text = postModel.namakaryawan
        viewHolder.jabatan.text = postModel.jabatan

        Picasso.get()
            .load("http://114.129.18.62:1000/assets/images/users/" + postModel.photo)
            .into(viewHolder.image)


    }
}

