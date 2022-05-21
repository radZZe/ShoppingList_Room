package com.mimoza_app.notes.shoppinglist_room.screens.shopitem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mimoza_app.notes.shoppinglist_room.R
import com.mimoza_app.notes.shoppinglist_room.databinding.FragmentShopItemBinding
import com.mimoza_app.notes.shoppinglist_room.models.ShopItem
import com.mimoza_app.notes.shoppinglist_room.utilits.*

class ShopItemFragment : Fragment() {

    private var _binding:FragmentShopItemBinding? = null
    private val mBinding get() = _binding!!
    lateinit private var typeLaunch:String
    private lateinit var mViewModel:ShopItemFragmentViewModel
    private lateinit var mCurrentItem:ShopItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShopItemBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }


    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        mViewModel = ViewModelProvider(this)[ShopItemFragmentViewModel::class.java]
        typeLaunch = arguments?.getSerializable(TYPE_LAUNCH).toString()
        if(typeLaunch == ADD_LAUNCH){
            mBinding.saveBtn.setOnClickListener{
                var name = mBinding.tiName.text.toString()
                var count = mBinding.tiCount.text.toString().toInt()
                mViewModel.insert(ShopItem(name = name,count = count,active = true)){
                    APP_ACTIVITY.navController.navigate(R.id.action_shopItemFragment_to_homeFragment)
                }
            }
        }else if(typeLaunch == EDIT_LAUNCH){
            mCurrentItem = arguments?.getSerializable(KEY_CLICK_ITEM) as ShopItem
            mBinding.tiName.setText(mCurrentItem.name)
            mBinding.tiCount.setText(mCurrentItem.count.toString())
            mBinding.saveBtn.setOnClickListener{
                mCurrentItem.name = mBinding.tiName.text.toString()
                mCurrentItem.count = mBinding.tiCount.text.toString().toInt()
                mViewModel.update(mCurrentItem){
                    APP_ACTIVITY.navController.navigate(R.id.action_shopItemFragment_to_homeFragment)
                }
            }
        }
    }

}