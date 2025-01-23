package com.tpsmedia.mysoejasch.holder

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.tpsmedia.materialx.ui.design.utils.Tools
import com.tpsmedia.mysoejasch.R
import com.tpsmedia.mysoejasch.databinding.ItemListViewsBinding
import com.tpsmedia.mysoejasch.model.Purchaserequest.Datum
import com.tpsmedia.mysoejasch.pronline.PRDetailActivity
import com.tpsmedia.mysoejasch.service.ServiceLogin
import com.xwray.groupie.viewbinding.BindableItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class ListViewHolder(var postModel: Datum, var activity: Activity) : BindableItem<ItemListViewsBinding>() {
    var serviceLogin: ServiceLogin = ServiceLogin(activity)
    @RequiresApi(Build.VERSION_CODES.O)
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    @RequiresApi(Build.VERSION_CODES.O)
    val targetFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    override fun getLayout(): Int = R.layout.item_list_views
    override fun initializeViewBinding(view: View): ItemListViewsBinding = ItemListViewsBinding.bind(view)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun bind(viewHolder: ItemListViewsBinding, position: Int) {


        viewHolder.txtTitle.text = postModel.noPPB
        viewHolder.txtSubtitle.text = postModel.ket

        viewHolder.cdview.setOnClickListener {
                val intent = Intent(activity, PRDetailActivity::class.java)
                intent.putExtra("NoPPB", postModel.noPPB)
                intent.putExtra("UcodePPB", postModel.UCodePPB)
                intent.putExtra("TglPPB", postModel.tglPPB)
                intent.putExtra("Ket", postModel.ket)
                intent.putExtra("Karyawan", postModel.nama_karyawan)
                intent.putExtra("Department", postModel.nama_Dept)

                intent.putExtra("approval_1", postModel.approval1)
                intent.putExtra("approval_2", postModel.approval2)
                intent.putExtra("approval_3", postModel.approval3)
                intent.putExtra("approval_4", postModel.approval4)

                intent.putExtra("no_approval_1", postModel.noApproval1)
                intent.putExtra("no_approval_2", postModel.noApproval2)
                intent.putExtra("no_approval_3", postModel.noApproval3)
                intent.putExtra("no_approval_4", postModel.noApproval4)

                intent.putExtra("cek_approval_1", postModel.cekApproval1)
                intent.putExtra("cek_approval_2", postModel.cekApproval2)
                intent.putExtra("cek_approval_3", postModel.cekApproval3)
                intent.putExtra("cek_approval_4", postModel.cekApproval4)

                activity.startActivity(intent)
        }


        val tanggal: LocalDate = LocalDate.parse(postModel.tglPBB.replace(" 00:00:00.000",""), formatter)
        val formattedDate = tanggal.format(targetFormat)
        viewHolder.txtDate.text = formattedDate.toString()

        if(serviceLogin.getprlevel().equals("1")){
            if(postModel.cekApproval2.equals("1") && postModel.cekApproval5.equals("1")){
                viewHolder.labeling.setBackgroundResource(R.drawable.badge_success)
                viewHolder.labeling.text = "Approve"
            }else if(postModel.cekApproval2.equals("2")){
                viewHolder.labeling.setBackgroundResource(R.drawable.badge_error)
                viewHolder.labeling.text = "Not Approve"
            }else{
                viewHolder.labeling.setBackgroundResource(R.drawable.badge_secondary)
                viewHolder.labeling.text = "Waiting"
            }

        }else if (serviceLogin.getprlevel().equals("5")){
            if(postModel.cekApproval3 == null){
                viewHolder.labeling.setBackgroundResource(R.drawable.badge_secondary)
                viewHolder.labeling.text = "Waiting"
            }else if(postModel.cekApproval3.equals("1")){
                viewHolder.labeling.setBackgroundResource(R.drawable.badge_success)
                viewHolder.labeling.text = "Approve"
            }else{
                viewHolder.labeling.setBackgroundResource(R.drawable.badge_error)
                viewHolder.labeling.text = "Cancel"
            }

        }else{
            if(postModel.cekApproval3 == null){
                viewHolder.labeling.setBackgroundResource(R.drawable.badge_secondary)
                viewHolder.labeling.text = "Waiting"
            }else if(postModel.cekApproval3.equals("1")){
                viewHolder.labeling.setBackgroundResource(R.drawable.badge_success)
                viewHolder.labeling.text = "Approve"
            }else{
                viewHolder.labeling.setBackgroundResource(R.drawable.badge_error)
                viewHolder.labeling.text = "Cancel"
            }
        }

        Tools.displayImageRound(activity, viewHolder.imageView3, "http://114.129.18.62:1000/assets/images/users/" + postModel.foto)



    }
}
