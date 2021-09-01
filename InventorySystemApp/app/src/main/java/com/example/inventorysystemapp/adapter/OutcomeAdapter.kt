package com.example.inventorysystemapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inventorysystemapp.databinding.OutcomeItemBinding
import com.example.inventorysystemapp.model.DetailOutcome

class OutcomeAdapter(private val lists : List<DetailOutcome>) : RecyclerView.Adapter<OutcomeAdapter.OutcomeViewHolder>() {

    inner class OutcomeViewHolder(val binding : OutcomeItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OutcomeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = OutcomeItemBinding.inflate(layoutInflater)
        return OutcomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OutcomeViewHolder, position: Int) {
        val outcome = lists.get(position)
        holder.binding.myOutcome = outcome
    }

    override fun getItemCount(): Int = lists.size
}