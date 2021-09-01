package com.example.studentmanagementoffline.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanagementoffline.R
import com.example.studentmanagementoffline.data.model.Kelas
import com.example.studentmanagementoffline.databinding.ClassHomeItemBinding

class ClassHomeAdapter : RecyclerView.Adapter<ClassHomeAdapter.MyViewHolder>() {

    private lateinit var mListKelas: List<Kelas>
    private lateinit var itemBinding: ClassHomeItemBinding

    inner class MyViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int) {
            itemBinding.tvClassName.text = mListKelas[position].namaKelas
        }

    }

    fun setList(listKelas: List<Kelas>) {
        mListKelas = listKelas
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        itemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.class_home_item, parent, false)
        return MyViewHolder(itemBinding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(position)

    override fun getItemCount(): Int = mListKelas.size
}