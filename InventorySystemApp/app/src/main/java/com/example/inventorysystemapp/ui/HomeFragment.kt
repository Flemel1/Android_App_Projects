package com.example.inventorysystemapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inventorysystemapp.R
import com.example.inventorysystemapp.adapter.RecyclerviewGoodsAdapter
import com.example.inventorysystemapp.adapter.RecyclerviewSliderAdapter
import com.example.inventorysystemapp.databinding.FragmentHomeBinding
import com.example.inventorysystemapp.factory.InventoryViewModelFactory
import com.example.inventorysystemapp.repository.InventoryRepository
import com.example.inventorysystemapp.viewmodel.InventoryViewModel

class HomeFragment : Fragment() {
    val TAG = "HomeFragment"

    private lateinit var binding : FragmentHomeBinding
    private val repository : InventoryRepository = InventoryRepository()
    private val inventoryViewModel: InventoryViewModel by activityViewModels() {
        InventoryViewModelFactory(repository)
    }
    private var myLists : ArrayList<Int> = ArrayList()
    private lateinit var adapter : RecyclerviewSliderAdapter
    private lateinit var goodsAdapter : RecyclerviewGoodsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, inventoryViewModel.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)
        myLists.clear()
        binding.recyclerviewSlider.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerviewGoodsStock.layoutManager = LinearLayoutManager(context)
        inventoryViewModel.myStock.observe(viewLifecycleOwner) { allStock ->
            myLists.add(allStock.itemCount)
            goodsAdapter = RecyclerviewGoodsAdapter(allStock.body,inventoryViewModel)
            binding.recyclerviewGoodsStock.swapAdapter(goodsAdapter, true)
        }
        inventoryViewModel.myCurrentIncome.observe(viewLifecycleOwner) { income ->
            myLists.add(income.currentIncome)
        }
        inventoryViewModel.myCurrentOutcome.observe(viewLifecycleOwner) { outcome ->
            myLists.add(outcome.currentOutcome)
            adapter = RecyclerviewSliderAdapter(myLists)
            binding.recyclerviewSlider.swapAdapter(adapter,true)
        }
        binding.btnAddStock.setOnClickListener {
            val action = HomeFragmentDirections.actionMoveToInsertItemStockFragment()
            it.findNavController().navigate(action)
            inventoryViewModel.saveStateUpdateOrDelete(false)
        }
        return binding.root
    }
}