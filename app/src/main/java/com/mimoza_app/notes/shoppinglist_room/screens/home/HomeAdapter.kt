package com.mimoza_app.notes.shoppinglist_room.screens.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.mimoza_app.notes.shoppinglist_room.R
import com.mimoza_app.notes.shoppinglist_room.models.ShopItem

class HomeAdapter: ListAdapter<ShopItem, HomeViewHolder>(HomeDiffCallback()) {

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener :((ShopItem)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view:View
        val layout = when(viewType){
            DISABLED_LAYOUT -> R.layout.item_shop_disabled
            ENABLED_LAYOUT -> R.layout.item_shop_enabled
            else-> throw RuntimeException("Unknown view type ${viewType}")
        }
        view = LayoutInflater.from(parent.context).inflate(layout,parent,false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val shopItem = getItem(position)
        holder.tv_content.text = shopItem.name
        holder.tv_count.text = shopItem.count.toString()
        holder.view.setOnClickListener{
            onShopItemClickListener?.invoke(shopItem)
            true
        }
        holder.view.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(getItem(position).active){
            return ENABLED_LAYOUT
        }else{
            return DISABLED_LAYOUT
        }
    }

    companion object{
        const val ENABLED_LAYOUT = 100
        const val DISABLED_LAYOUT = 101
        const val MAX_POOL_SIZE = 15
    }
}