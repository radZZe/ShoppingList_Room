package com.mimoza_app.notes.shoppinglist_room.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mimoza_app.notes.shoppinglist_room.models.ShopItem

@Dao
interface AppRoomDao {
        @Query("SELECT * from shopItem_tables")
        fun getAllItems(): LiveData<List<ShopItem>>

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(item: ShopItem)

        @Delete
        suspend fun delete(item: ShopItem)

        @Update
        suspend fun update(item:ShopItem)
}
