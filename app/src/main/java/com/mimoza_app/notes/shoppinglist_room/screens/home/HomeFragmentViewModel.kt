package com.mimoza_app.notes.shoppinglist_room.screens.home

import android.app.Application
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mimoza_app.notes.shoppinglist_room.R
import com.mimoza_app.notes.shoppinglist_room.database.AppRoomDatabase
import com.mimoza_app.notes.shoppinglist_room.database.AppRoomRepository
import com.mimoza_app.notes.shoppinglist_room.models.ShopItem
import com.mimoza_app.notes.shoppinglist_room.utilits.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragmentViewModel(application: Application) : AndroidViewModel(application) {
    var mContext = application
    lateinit var allShopItems: LiveData<List<ShopItem>>

    fun initDatabase(type: String) {
        when (type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(mContext).getAppRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                allShopItems = REPOSITORY.allShopItems
            }
        }
    }


    fun updateItem(item:ShopItem,onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            REPOSITORY.update(item){}
        }
        onSuccess()
    }

    fun deleteItem(item:ShopItem,onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            REPOSITORY.delete(item){}
        }
        onSuccess()
    }

    fun changeStateItem(shopItem: ShopItem){
        var newItem = shopItem.copy(active = !shopItem.active)
        updateItem(newItem){
        }
    }

    fun onItemClick(item:ShopItem){
        var bundle = Bundle()
        bundle.putSerializable(KEY_CLICK_ITEM,item)
        bundle.putSerializable(TYPE_LAUNCH, EDIT_LAUNCH)
        APP_ACTIVITY.navController.navigate(R.id.action_homeFragment_to_shopItemFragment,bundle)
    }
}