package com.example.coronaapp.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.coronaapp.R
import com.example.coronaapp.data.entities.CoronaIndonesia
import com.example.coronaapp.data.util.Contract.Companion.imageResourceID
import com.example.coronaapp.data.util.Contract.Companion.textTitle
import com.example.coronaapp.databinding.HomeItemBinding

class IndonesiaCaseAdapter : RecyclerView.Adapter<IndonesiaCaseAdapter.MyViewHolder>() {

    private var allIndonesiaCaseList : ArrayList<CoronaIndonesia> = ArrayList<CoronaIndonesia>()

    inner class MyViewHolder(val binding : HomeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            when (position) {
                0 -> {
                    binding.imageIcon.setImageResource(imageResourceID[position])
                    binding.textStatusHomeItem.text = textTitle[position]
                    binding.textTotallyCase.text = allIndonesiaCaseList.get(0).positif
                }
                1 -> {
                    binding.imageIcon.setImageResource(imageResourceID[position])
                    binding.textStatusHomeItem.text = textTitle[position]
                    binding.textTotallyCase.text = allIndonesiaCaseList.get(0).sembuh
                }
                2 -> {
                    binding.imageIcon.setImageResource(imageResourceID[position])
                    binding.textStatusHomeItem.text = textTitle[position]
                    binding.textTotallyCase.text = allIndonesiaCaseList.get(0).meninggal
                }
            }
        }
    }

    fun setList(lists : ArrayList<CoronaIndonesia>) {
        allIndonesiaCaseList.clear()
        allIndonesiaCaseList = lists
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : HomeItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.home_item,
            parent,
            false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(position)

    override fun getItemCount(): Int = 3
}