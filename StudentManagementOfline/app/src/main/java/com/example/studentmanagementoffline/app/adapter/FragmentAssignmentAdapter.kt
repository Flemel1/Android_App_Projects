package com.example.studentmanagementoffline.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanagementoffline.R
import com.example.studentmanagementoffline.app.utils.RecyclerViewPopupMenu
import com.example.studentmanagementoffline.data.model.Tugas
import com.example.studentmanagementoffline.databinding.AssignmentItemBinding

class FragmentAssignmentAdapter : RecyclerView.Adapter<FragmentAssignmentAdapter.MyViewHolder>() {

    private lateinit var mCallback: RecyclerViewPopupMenu
    private lateinit var itemBinding: AssignmentItemBinding
    private lateinit var listTugas: ArrayList<Tugas>

    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int) {
            itemBinding.tvTitleAssignment.text = listTugas[position].namaTugas
            itemBinding.tvAssignmentForClass.text = listTugas[position].namaKelas
            itemBinding.tvDetailAssignment.text = "Deskripsi: ${listTugas[position].deskripsiTugas}"
            val popUpMenu = PopupMenu(itemBinding.root.context, itemView)
            popUpMenu.setOnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.menu_update -> {
                        mCallback.updateSelectedItem(itemView,listTugas[position])
                        true
                    }
                    else -> false
                }
            }
            popUpMenu.inflate(R.menu.custom_context_menu)
            view.setOnLongClickListener {
                popUpMenu.show()
                true
            }
        }
    }


    fun setLists(lists: List<Tugas>) {
        listTugas = lists as ArrayList<Tugas>
    }

    fun setCallbackPopupMenu(callback: RecyclerViewPopupMenu) {
        mCallback = callback
    }

    fun deleteItem(position: Int) {
        listTugas.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        itemBinding = DataBindingUtil.inflate(inflater, R.layout.assignment_item, parent, false)
        return MyViewHolder(itemBinding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(position)

    override fun getItemCount(): Int = listTugas.size
}