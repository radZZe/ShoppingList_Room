package com.mimoza_app.notes.shoppinglist_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.mimoza_app.notes.shoppinglist_room.databinding.ActivityMainBinding
import com.mimoza_app.notes.shoppinglist_room.utilits.APP_ACTIVITY

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    private var _binding: ActivityMainBinding? = null
    val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        APP_ACTIVITY = this
        setContentView(mBinding.root)
        navController = Navigation.findNavController(this,R.id.nav_host)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}