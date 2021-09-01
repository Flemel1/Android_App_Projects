package com.example.studentmanagementoffline.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanagementoffline.R
import com.example.studentmanagementoffline.data.model.Nilai
import com.example.studentmanagementoffline.databinding.ScoreItemBinding

class ScoreAdapter : RecyclerView.Adapter<ScoreAdapter.ViewHolder>() {

    private lateinit var bindingScoreItemBinding: ScoreItemBinding
    private lateinit var listNilai: ArrayList<Nilai>

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int) {
            val assignmentName = listNilai[position].tugasID.toString()
            val score = listNilai[position].nilai.toString()
            bindingScoreItemBinding.tvAssignmentName.text = assignmentName
            bindingScoreItemBinding.tvScore.text = score
        }

    }

    fun setList(list: List<Nilai>) {
        listNilai = list as ArrayList<Nilai>
    }

    fun deleteItem(position: Int) {
        listNilai.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        bindingScoreItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.score_item, parent, false)
        return ViewHolder(bindingScoreItemBinding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position)

    override fun getItemCount(): Int = listNilai.size
}