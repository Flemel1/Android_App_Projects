package com.example.studentmanagementoffline.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanagementoffline.R
import com.example.studentmanagementoffline.app.utils.RecyclerViewPopupMenu
import com.example.studentmanagementoffline.data.model.Siswa
import com.example.studentmanagementoffline.databinding.StudentItemBinding

class StudentAdapter(private val callback: RecyclerViewOnClickItem) :
    RecyclerView.Adapter<StudentAdapter.MyViewHolder>() {

    private lateinit var mListAverages: List<Double>
    private lateinit var callbackPopupMenu: RecyclerViewPopupMenu
    private lateinit var studentItemBinding: StudentItemBinding
    private lateinit var listSiswa: ArrayList<Siswa>

    interface RecyclerViewOnClickItem {
        fun onClickedItem(view: View, siswa: Siswa)
    }

    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int) {
            studentItemBinding.tvStudentName.text = listSiswa[position].namaSiswa
            if (mListAverages.size > position) {
                studentItemBinding.tvAverage.text = "Rata-rata: ${mListAverages[position]}"
            } else {
                studentItemBinding.tvAverage.text = "Rata-rata: 0"
            }
            val popupMenu = PopupMenu(studentItemBinding.root.context, itemView)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_update -> {
                        callbackPopupMenu.updateSelectedItem(itemView, listSiswa[position])
                        true
                    }
                    else -> false
                }
            }
            popupMenu.inflate(R.menu.custom_context_menu)
            view.setOnClickListener {
                val siswa = listSiswa[position]
                callback.onClickedItem(itemView, siswa)
            }
            view.setOnLongClickListener {
                popupMenu.show()
                true
            }
        }
    }

    fun setList(list: List<Siswa>, listAverages: List<Double>) {
        listSiswa = list as ArrayList<Siswa>
        mListAverages = listAverages
    }

    fun deleteItem(position: Int) {
        listSiswa.removeAt(position)
        notifyItemRemoved(position)
    }

    fun setCallbackPopupMenu(callback: RecyclerViewPopupMenu) {
        callbackPopupMenu = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        studentItemBinding = DataBindingUtil.inflate(inflater, R.layout.student_item, parent, false)
        return MyViewHolder(studentItemBinding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(position)

    override fun getItemCount(): Int = listSiswa.size
}