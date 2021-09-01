package com.example.inventorysystemapp.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.inventorysystemapp.ui.IncomeDetailFragment
import com.example.inventorysystemapp.ui.OutcomeDetailFragment

class ManagementStockAdapter(fragment : Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment = Fragment()
        if (position == 0) {
            fragment = IncomeDetailFragment()
        }
        else if(position == 1) {
            fragment = OutcomeDetailFragment()
        }
        return fragment
    }
}