package com.mimoza_app.notes.shoppinglist_room.database

import androidx.lifecycle.LiveData
import com.mimoza_app.notes.shoppinglist_room.models.ShopItem

interface DatabaseRepository {
    val allShopItems: LiveData<List<ShopItem>>
    suspend fun insert(item: ShopItem, onSuccess: () -> Unit)
    suspend fun delete(item: ShopItem, onSuccess: () -> Unit)
    suspend fun update(item:ShopItem, onSuccess: () -> Unit)
}