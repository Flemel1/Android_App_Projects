package com.example.coronaapp.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.coronaapp.R
import com.example.coronaapp.data.util.Contract.Companion.infoImageResourceID
import com.example.coronaapp.databinding.InfoItemBinding

class InfoAdapter : RecyclerView.Adapter<InfoAdapter.ViewHolderInfo>() {

    private lateinit var mTitles : Array<String>
    private lateinit var mDesc : Array<String>

    inner class ViewHolderInfo(private val binding : InfoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.imageInfoItem.setImageResource(infoImageResourceID[position])
            binding.titleInfoMenu.text = mTitles[position]
            binding.descInfoMenu.text = mDesc[position]
        }
    }

    fun setTitleAndDesc(titles : Array<String>, descriptions : Array<String>) {
        mTitles = titles
        mDesc = descriptions
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderInfo {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : InfoItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.info_item,
            parent,
            false
        )

        return ViewHolderInfo(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderInfo, position: Int) = holder.bind(position)

    override fun getItemCount(): Int = 3
}