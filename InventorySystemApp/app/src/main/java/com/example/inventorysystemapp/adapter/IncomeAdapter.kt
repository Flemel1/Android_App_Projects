package com.example.inventorysystemapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inventorysystemapp.databinding.IncomeItemBinding
import com.example.inventorysystemapp.model.DetailIncome

class IncomeAdapter(private val lists : List<DetailIncome>) : RecyclerView.Adapter<IncomeAdapter.IncomeViewHolder>() {

    inner class IncomeViewHolder(val binding : IncomeItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = IncomeItemBinding.inflate(layoutInflater)
        return IncomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IncomeViewHolder, position: Int) {
        val income = lists.get(position)
        holder.binding.myIncome = income
    }

    override fun getItemCount(): Int = lists.size
}