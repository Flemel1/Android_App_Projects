package com.example.inventorysystemapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.inventorysystemapp.R
import com.example.inventorysystemapp.adapter.ManagementStockAdapter
import com.example.inventorysystemapp.databinding.FragmentManagementStockBinding
import com.google.android.material.tabs.TabLayoutMediator

class ManagementStockFragment : Fragment() {

    private lateinit var viewPagerAdapter : ManagementStockAdapter
    private lateinit var binding : FragmentManagementStockBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_management_stock, container, false)
        viewPagerAdapter = ManagementStockAdapter(this)
        binding.myViewPager2.adapter = viewPagerAdapter
        TabLayoutMediator(binding.myTabLayout, binding.myViewPager2) { tab, position ->
            if (position == 0) {
                tab.text = "Income"
            }
            else if(position == 1) {
                tab.text = "Outcome"
            }
        }.attach()
        return binding.root
    }
}