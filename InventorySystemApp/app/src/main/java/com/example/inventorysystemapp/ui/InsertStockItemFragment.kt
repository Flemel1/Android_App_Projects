package com.example.inventorysystemapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.inventorysystemapp.R
import com.example.inventorysystemapp.databinding.FragmentInsertStockItemBinding
import com.example.inventorysystemapp.factory.InventoryViewModelFactory
import com.example.inventorysystemapp.repository.InventoryRepository
import com.example.inventorysystemapp.viewmodel.InventoryViewModel

class InsertStockItemFragment : Fragment() {

    val TAG = "InsertStockItemFragment"

    private lateinit var binding : FragmentInsertStockItemBinding
    private val repository: InventoryRepository = InventoryRepository()
    private val inventoryViewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, inventoryViewModel.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_insert_stock_item, container, false)
        inventoryViewModel.updateOrDeleteState.observe(viewLifecycleOwner) { state ->
            if (state == true) {
                val myStock = inventoryViewModel.myStockItem.value
                val id = myStock!!.idBarang
                val name = myStock.name
                val stock = myStock.stock
                binding.btnInsertStockItem.text = "Update Item"
                binding.textInputIdBarang.setText(id)
                binding.textInputNamaBarang.setText(name)
                binding.textInputStockBarang.setText(stock)
                binding.btnInsertStockItem.setOnClickListener {
                    checkInputUpdateItem(id,name,stock)
                }
                binding.btnDeleteStockItem.setOnClickListener {
                    checkInputDeleteItem(id,name,stock)
                }
            }
            else {
                binding.btnDeleteStockItem.visibility = View.GONE
                binding.btnInsertStockItem.setOnClickListener {
                    val id = binding.textInputIdBarang.text.toString()
                    val name = binding.textInputNamaBarang.text.toString()
                    val stock = binding.textInputStockBarang.text.toString()
                    checkInputInsertItem(id,name,stock)
                }
            }
        }
        return binding.root
    }

    private fun checkInputDeleteItem(id: String, name: String, stock: String) {
        if (id.isBlank() || name.isBlank() || stock.isBlank()) {
            Toast.makeText(context, "data tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }
        else {
            inventoryViewModel.deleteStockItem(id).observe(viewLifecycleOwner) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                binding.textInputIdBarang.text = null
                binding.textInputNamaBarang.text = null
                binding.textInputStockBarang.text = null
            }
        }
    }

    private fun checkInputUpdateItem(id: String, name: String, stock: String) {
        if (id.isBlank() || name.isBlank() || stock.isBlank()) {
            Toast.makeText(context, "data tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }
        else {
            inventoryViewModel.updateStockItem(id, name, stock).observe(viewLifecycleOwner) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                binding.textInputIdBarang.text = null
                binding.textInputNamaBarang.text = null
                binding.textInputStockBarang.text = null
            }
        }
    }

    private fun checkInputInsertItem(id : String, name : String, stock : String) {
        if (id.isBlank() || name.isBlank() || stock.isBlank()) {
            Toast.makeText(context, "data tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }
        else {
            inventoryViewModel.insertStockItem(id, name, stock).observe(viewLifecycleOwner) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                binding.textInputIdBarang.text = null
                binding.textInputNamaBarang.text = null
                binding.textInputStockBarang.text = null
            }
        }
    }
}