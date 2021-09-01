package com.example.studentmanagementoffline.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanagementoffline.R
import com.example.studentmanagementoffline.app.utils.RecyclerViewPopupMenu
import com.example.studentmanagementoffline.data.model.Kelas
import com.example.studentmanagementoffline.databinding.ClassItemBinding

class FragmentClassAdapter(val callback: RecyclerViewClickItem) :
    RecyclerView.Adapter<FragmentClassAdapter.MyViewHolder>() {

    private lateinit var mListKelas: ArrayList<Kelas>
    private lateinit var mListStudentTotal: List<Int>
    private lateinit var listBinding: ClassItemBinding
    private lateinit var callbackPopupMenu: RecyclerViewPopupMenu

    interface RecyclerViewClickItem {
        fun onClickedItem(view: View, className: String)
    }

    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int) {
            listBinding.tvTitleClass.text = mListKelas[position].namaKelas
            if (mListStudentTotal.size > position)
                listBinding.tvDetailClass.text = "${mListStudentTotal[position]} Siswa"
            else
                listBinding.tvDetailClass.text = "0 Siswa"
            val popupMenu = PopupMenu(listBinding.root.context, itemView)
            popupMenu.setOnMenuItemClickListener {  item ->
                when(item.itemId) {
                    R.id.menu_update -> {
                        callbackPopupMenu.updateSelectedItem(itemView, mListKelas[position])
                        true
                    }
                    else -> false
                }
            }
            popupMenu.inflate(R.menu.custom_context_menu)
            view.setOnClickListener {
                val className = mListKelas[position].namaKelas
                callback.onClickedItem(itemView, className)
            }
            view.setOnLongClickListener {
                popupMenu.show()
                true
            }
        }
    }

    fun setList(listKelas: List<Kelas>, listStudentTotal: List<Int>) {
        mListKelas = listKelas as ArrayList<Kelas>
        mListStudentTotal = listStudentTotal
    }


    fun setCallbackPopupMenu(callback: RecyclerViewPopupMenu) {
        callbackPopupMenu = callback
    }

    fun deleteItem(position: Int) {
        mListKelas.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        listBinding = ClassItemBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(listBinding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(position)

    override fun getItemCount(): Int = mListKelas.size
}
