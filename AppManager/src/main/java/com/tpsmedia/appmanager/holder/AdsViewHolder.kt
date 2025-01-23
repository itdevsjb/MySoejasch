package com.tpsmedia.appmanager.holder


import android.app.Activity
import android.view.View
import com.tpsmedia.appmanager.AdsManager
import com.tpsmedia.appmanager.R
import com.tpsmedia.appmanager.databinding.ItemAdsViewBinding
import com.xwray.groupie.viewbinding.BindableItem


class AdsViewHolder(var activity: Activity, var ORDER: Int, var PAGE: String) : BindableItem<ItemAdsViewBinding>() {
    override fun getLayout(): Int = R.layout.item_ads_view
    override fun initializeViewBinding(view: View): ItemAdsViewBinding =
        ItemAdsViewBinding.bind(view)

    override fun bind(viewHolder: ItemAdsViewBinding, position: Int) {
        AdsManager().initNative(activity, viewHolder.lyNativeAds, ORDER, PAGE)
    }


}
