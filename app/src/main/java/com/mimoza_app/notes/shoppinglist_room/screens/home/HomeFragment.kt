package com.mimoza_app.notes.shoppinglist_room.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.mimoza_app.notes.shoppinglist_room.R
import com.mimoza_app.notes.shoppinglist_room.databinding.FragmentHomeBinding
import com.mimoza_app.notes.shoppinglist_room.models.ShopItem
import com.mimoza_app.notes.shoppinglist_room.utilits.ADD_LAUNCH
import com.mimoza_app.notes.shoppinglist_room.utilits.APP_ACTIVITY
import com.mimoza_app.notes.shoppinglist_room.utilits.TYPE_LAUNCH
import com.mimoza_app.notes.shoppinglist_room.utilits.TYPE_ROOM

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var  homeAdapter:HomeAdapter
    private lateinit var mObserver: Observer<List<ShopItem>>
    private lateinit var mViewModel:HomeFragmentViewModel
    private lateinit var rvMain: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        mViewModel = ViewModelProvider(this)[HomeFragmentViewModel::class.java]
        mViewModel.initDatabase(TYPE_ROOM)
        mObserver = Observer{
            homeAdapter.submitList(it.asReversed())
        }
        mViewModel.allShopItems.observe(this,mObserver)
        setupRecyclerView()
        mBinding.btnAddItem.setOnClickListener {
            var bundle = Bundle()
            bundle.putSerializable(TYPE_LAUNCH, ADD_LAUNCH)
            APP_ACTIVITY.navController.navigate(R.id.action_homeFragment_to_shopItemFragment,bundle)
        }
    }

    private fun setupRecyclerView(){
        rvMain = mBinding.rvMain
        with(rvMain){
            homeAdapter = HomeAdapter()
            adapter = homeAdapter
        }

        setupOnLongClickListener()
        setupOnClickListener()
        setupSwipeListener(rvMain)
    }


    private fun setupSwipeListener(rvShopList: RecyclerView) {
        val callBack = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = homeAdapter.currentList[viewHolder.adapterPosition]
                mViewModel.deleteItem(item){}
            }

        }
        val itemTouchHelper = ItemTouchHelper(callBack)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }

    private fun setupOnLongClickListener(){
        homeAdapter.onShopItemLongClickListener = {
            mViewModel.changeStateItem(it)
        }
    }

    private fun setupOnClickListener(){
        homeAdapter.onShopItemClickListener ={
            mViewModel.onItemClick(it)
        }
    }

}