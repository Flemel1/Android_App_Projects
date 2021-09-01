package com.example.inventorysystemapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inventorysystemapp.R
import com.example.inventorysystemapp.adapter.IncomeAdapter
import com.example.inventorysystemapp.databinding.FragmentIncomeDetailBinding
import com.example.inventorysystemapp.factory.InventoryViewModelFactory
import com.example.inventorysystemapp.repository.InventoryRepository
import com.example.inventorysystemapp.viewmodel.InventoryViewModel

class IncomeDetailFragment : Fragment() {

    private val repository = InventoryRepository()
    private val inventoryViewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(repository)
    }

    private lateinit var binding: FragmentIncomeDetailBinding
    private lateinit var adapter: IncomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_income_detail, container, false)
        binding.recyclerViewIncome.layoutManager = LinearLayoutManager(context)
        inventoryViewModel.myAllIncome.observe(viewLifecycleOwner) { allIncome ->
            adapter = IncomeAdapter(allIncome.body)
            binding.recyclerViewIncome.swapAdapter(adapter, true)
        }
        binding.btnAddIncome.setOnClickListener {
            val action = ManagementStockFragmentDirections.actionManagementStockFragmentToInsertIncomeOutcomeFragment()
            it.findNavController().navigate(action)
            inventoryViewModel.saveStateInsertIncome()
        }
        return binding.root
    }
}