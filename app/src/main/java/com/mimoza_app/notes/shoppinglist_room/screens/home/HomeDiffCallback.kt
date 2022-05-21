package com.mimoza_app.notes.shoppinglist_room.screens.home

import androidx.recyclerview.widget.DiffUtil
import com.mimoza_app.notes.shoppinglist_room.models.ShopItem

class HomeDiffCallback: DiffUtil.ItemCallback<ShopItem>() {
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem == newItem
    }
}