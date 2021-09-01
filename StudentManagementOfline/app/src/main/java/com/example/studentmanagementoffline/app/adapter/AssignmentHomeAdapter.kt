package com.example.studentmanagementoffline.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanagementoffline.R
import com.example.studentmanagementoffline.data.model.Tugas
import com.example.studentmanagementoffline.databinding.AssignmentHomeItemBinding

class AssignmentHomeAdapter : RecyclerView.Adapter<AssignmentHomeAdapter.MyViewHolder>() {

    private lateinit var bindingItem: AssignmentHomeItemBinding
    private lateinit var listTugas: List<Tugas>

    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int) {
            bindingItem.tvClassName.text = listTugas[position].namaKelas
            bindingItem.tvDescription.text = "Deskripsi: ${listTugas[position].deskripsiTugas}"
            bindingItem.tvDeadline.text = "Deadline: ${listTugas[position].deadlineTugas}"
        }
    }

    fun setList(lists: List<Tugas>) {
        listTugas = lists
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        bindingItem =
            DataBindingUtil.inflate(inflater, R.layout.assignment_home_item, parent, false)
        return MyViewHolder(bindingItem.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(position)

    override fun getItemCount(): Int = listTugas.size
}