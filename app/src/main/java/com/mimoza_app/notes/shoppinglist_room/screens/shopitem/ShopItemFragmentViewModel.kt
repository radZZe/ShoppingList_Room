package com.mimoza_app.notes.shoppinglist_room.screens.shopitem

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mimoza_app.notes.shoppinglist_room.models.ShopItem
import com.mimoza_app.notes.shoppinglist_room.utilits.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShopItemFragmentViewModel(application: Application): AndroidViewModel(application)  {
    fun insert(item:ShopItem, onSuccess:()->Unit){
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insert(item){
            }
        }
        onSuccess()
    }

    fun update(item:ShopItem,onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.update(item){

            }
        }
        onSuccess()
    }
}