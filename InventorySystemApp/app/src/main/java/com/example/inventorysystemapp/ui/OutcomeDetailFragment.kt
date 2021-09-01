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
import com.example.inventorysystemapp.adapter.OutcomeAdapter
import com.example.inventorysystemapp.databinding.FragmentOutcomeDetailBinding
import com.example.inventorysystemapp.factory.InventoryViewModelFactory
import com.example.inventorysystemapp.repository.InventoryRepository
import com.example.inventorysystemapp.viewmodel.InventoryViewModel

class OutcomeDetailFragment : Fragment() {

    private val repository = InventoryRepository()
    private val inventoryViewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(repository)
    }

    private lateinit var binding: FragmentOutcomeDetailBinding
    private lateinit var adapter: OutcomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_outcome_detail, container, false)
        binding.recyclerViewOutcome.layoutManager = LinearLayoutManager(context)
        inventoryViewModel.myAllOutcome.observe(viewLifecycleOwner) { allOutcome ->
            adapter = OutcomeAdapter(allOutcome.body)
            binding.recyclerViewOutcome.swapAdapter(adapter, true)
        }
        binding.btnAddOutcome.setOnClickListener {
            val action = ManagementStockFragmentDirections.actionManagementStockFragmentToInsertIncomeOutcomeFragment()
            it.findNavController().navigate(action)
            inventoryViewModel.saveStateInsertOutcome()
        }
        return binding.root
    }
}