package com.example.coronaapp.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.coronaapp.R
import com.example.coronaapp.data.util.Contract.Companion.bantuanImageResourceID
import com.example.coronaapp.databinding.BantuanItemBinding

class BantuanAdapter : RecyclerView.Adapter<BantuanAdapter.ViewHolderBantuan>() {

    private lateinit var mTitles : Array<String>

    inner class ViewHolderBantuan(
            private val binding : BantuanItemBinding
        ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.imageInfoItem.setImageResource(bantuanImageResourceID[position])
        }
    }

    fun setTitleAndDesc(titles : Array<String>) {
        mTitles = titles
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BantuanAdapter.ViewHolderBantuan {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : BantuanItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.bantuan_item,
            parent,
            false
        )
        return ViewHolderBantuan(binding)
    }

    override fun onBindViewHolder(holder: BantuanAdapter.ViewHolderBantuan, position: Int) = holder.bind(position)

    override fun getItemCount(): Int = 2
}