package com.example.inventorysystemapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.inventorysystemapp.R
import com.example.inventorysystemapp.databinding.RecyclerviewSliderItemBinding

class RecyclerviewSliderAdapter(private var lists: List<Int>) : RecyclerView.Adapter<RecyclerviewSliderAdapter.MyViewHolder>() {

    private lateinit var binding : RecyclerviewSliderItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.recyclerview_slider_item, parent, false)
        val view = binding.root
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(lists.get(position).toString())

    override fun getItemCount(): Int = lists.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(countItem : String) {
            binding.tvCount.text = countItem
        }

    }
}

