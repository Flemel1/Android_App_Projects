package com.example.coronaapp.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.coronaapp.R
import com.example.coronaapp.data.entities.Died
import com.example.coronaapp.data.entities.Positif
import com.example.coronaapp.data.entities.Sembuh
import com.example.coronaapp.data.util.Contract
import com.example.coronaapp.databinding.HomeItemBinding

class WorldCaseAdapter : RecyclerView.Adapter<WorldCaseAdapter.MyViewHolder>() {

    lateinit var mPositif: Positif
    lateinit var mSembuh: Sembuh
    lateinit var mDied: Died

    inner class MyViewHolder(private val binding : HomeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            when (position) {
                0 -> {
                    binding.imageIcon.setImageResource(Contract.imageResourceID[position])
                    binding.textStatusHomeItem.text = Contract.textTitle[position]
                    binding.textTotallyCase.text = mPositif.value
                }
                1 -> {
                    binding.imageIcon.setImageResource(Contract.imageResourceID[position])
                    binding.textStatusHomeItem.text = Contract.textTitle[position]
                    binding.textTotallyCase.text = mSembuh.value
                }
                2 -> {
                    binding.imageIcon.setImageResource(Contract.imageResourceID[position])
                    binding.textStatusHomeItem.text = Contract.textTitle[position]
                    binding.textTotallyCase.text = mDied.value
                }
            }
        }
    }

    fun setList(positif: Positif, sembuh: Sembuh, died: Died) {
        mPositif = positif
        mSembuh = sembuh
        mDied = died
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : HomeItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.home_item,
            parent,
            false
        )

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(position)

    override fun getItemCount(): Int = 3
}