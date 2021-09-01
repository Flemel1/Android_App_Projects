package com.example.inventorysystemapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.inventorysystemapp.R
import com.example.inventorysystemapp.databinding.FragmentInsertIncomeOutcomeBinding
import com.example.inventorysystemapp.factory.InventoryViewModelFactory
import com.example.inventorysystemapp.repository.InventoryRepository
import com.example.inventorysystemapp.viewmodel.InventoryViewModel

class InsertIncomeOutcomeFragment : Fragment() {

    private val repository = InventoryRepository()
    private val inventoryViewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(repository)
    }

    private lateinit var binding: FragmentInsertIncomeOutcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_insert_income_outcome, container, false)
        inventoryViewModel.incomeState.observe(viewLifecycleOwner) {
            if (it == true) {
                binding.textLayoutPengirimPenerima.hint = "Penerima"
            }
        }
        inventoryViewModel.outcomeState.observe(viewLifecycleOwner) {
            if (it == true) {
                binding.textLayoutPengirimPenerima.hint = "Pengirim"
            }
        }
        binding.btnInsertIncomeOutcome.setOnClickListener {
            val id = binding.textInputIdBarang.text.toString()
            val name = binding.textInputNamaBarang.text.toString()
            val stock = binding.textInputStockBarang.text.toString()
            val pengirimOrpenerima = binding.textInputPenerimaPengirim.text.toString()
            if (binding.textLayoutPengirimPenerima.hint == "Pengirim") {
                insertOutcome(id,name,stock,pengirimOrpenerima)
            }
            else if (binding.textLayoutPengirimPenerima.hint == "Penerima") {
                insertIncome(id,name,stock,pengirimOrpenerima)
            }
        }
        return binding.root
    }

    private fun insertIncome(id: String, name: String, stock: String, penerima: String) {
        if (id.isBlank() || name.isBlank() || stock.isBlank() || penerima.isBlank()) {
            Toast.makeText(context, "data tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }
        else {
            inventoryViewModel.insertIncome(id,name,stock,penerima).observe(viewLifecycleOwner) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                binding.textInputIdBarang.text = null
                binding.textInputNamaBarang.text = null
                binding.textInputStockBarang.text = null
                binding.textInputPenerimaPengirim.text = null
            }
        }
    }

    private fun insertOutcome(id: String, name: String, stock: String, pengirim: String) {
        if (id.isBlank() || name.isBlank() || stock.isBlank() || pengirim.isBlank()) {
            Toast.makeText(context, "data tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }
        else {
            inventoryViewModel.insertOutcome(id,name,stock,pengirim).observe(viewLifecycleOwner) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                binding.textInputIdBarang.text = null
                binding.textInputNamaBarang.text = null
                binding.textInputStockBarang.text = null
                binding.textInputPenerimaPengirim.text = null
            }
        }
    }
}