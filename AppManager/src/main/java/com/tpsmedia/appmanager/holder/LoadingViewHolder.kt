package com.tpsmedia.appmanager.holder


import android.view.View
import com.tpsmedia.appmanager.R
import com.tpsmedia.appmanager.databinding.ItemLoadingViewBinding
import com.xwray.groupie.viewbinding.BindableItem


class LoadingViewHolder() : BindableItem<ItemLoadingViewBinding>() {
    override fun getLayout(): Int = R.layout.item_loading_view
    override fun initializeViewBinding(view: View): ItemLoadingViewBinding =
        ItemLoadingViewBinding.bind(view)

    override fun bind(viewHolder: ItemLoadingViewBinding, position: Int) {
    }


}
