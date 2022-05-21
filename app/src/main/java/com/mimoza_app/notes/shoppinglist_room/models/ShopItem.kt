package com.mimoza_app.notes.shoppinglist_room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "shopItem_tables")
data class ShopItem(
    @ColumnInfo var name: String,
    @ColumnInfo var count: Int,
    @ColumnInfo val active: Boolean,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
): Serializable