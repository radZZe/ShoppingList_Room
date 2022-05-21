package com.mimoza_app.notes.shoppinglist_room.database

import androidx.lifecycle.LiveData
import com.mimoza_app.notes.shoppinglist_room.models.ShopItem

class AppRoomRepository(private val appRoomDao: AppRoomDao):DatabaseRepository  {
    override val allShopItems: LiveData<List<ShopItem>>
        get() = appRoomDao.getAllItems()

    override suspend fun insert(item: ShopItem, onSuccess: () -> Unit) {
        appRoomDao.insert(item)
        onSuccess()
    }

    override suspend fun delete(item: ShopItem, onSuccess: () -> Unit) {
        appRoomDao.delete(item)
        onSuccess()
    }

    override suspend fun update(item: ShopItem, onSuccess: () -> Unit) {
        appRoomDao.update(item)
        onSuccess()
    }
}