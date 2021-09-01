package com.example.inventorysystemapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.inventorysystemapp.databinding.RecyclerviewGoodsItemBinding
import com.example.inventorysystemapp.model.StockItem
import com.example.inventorysystemapp.ui.HomeFragmentDirections
import com.example.inventorysystemapp.viewmodel.InventoryViewModel

class RecyclerviewGoodsAdapter(private var lists : List<StockItem>, private val viewModel: InventoryViewModel) :
    RecyclerView.Adapter<RecyclerviewGoodsAdapter.GoodsViewHolder>() {


    inner class GoodsViewHolder(val binding: RecyclerviewGoodsItemBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewGoodsItemBinding.inflate(layoutInflater)
        return GoodsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GoodsViewHolder, position: Int) {
        val stockItem = lists.get(position)
        holder.binding.myStockItem = stockItem
        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionMoveToInsertItemStockFragment()
            it.findNavController().navigate(action)
            viewModel.saveStateUpdateOrDelete(true)
            viewModel.passStockItemForUpdateOrDelete(stockItem)
        }
    }

    override fun getItemCount(): Int = lists.size
}