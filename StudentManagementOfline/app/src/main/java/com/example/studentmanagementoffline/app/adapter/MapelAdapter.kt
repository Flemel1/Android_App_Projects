package com.example.studentmanagementoffline.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanagementoffline.R
import com.example.studentmanagementoffline.app.utils.RecyclerViewPopupMenu
import com.example.studentmanagementoffline.data.model.MataPelajaran
import com.example.studentmanagementoffline.databinding.MapelItemBinding

class MapelAdapter : RecyclerView.Adapter<MapelAdapter.MyViewHolder>() {

    private lateinit var mListMapel: ArrayList<MataPelajaran>
    private lateinit var itemBinding: MapelItemBinding
    private lateinit var callback: RecyclerViewPopupMenu

    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int) {
            itemBinding.tvMapel.text = mListMapel[position].namaMapel
            val popupMenu = PopupMenu(itemBinding.root.context, itemView)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_update -> {
                        callback.updateSelectedItem(itemView, mListMapel[position])
                        true
                    }
                    else -> false
                }
            }
            popupMenu.inflate(R.menu.custom_context_menu)
            view.setOnLongClickListener {
                popupMenu.show()
                true
            }
        }
    }

    fun setList(listMapel: List<MataPelajaran>) {
        mListMapel = listMapel as ArrayList<MataPelajaran>
    }

    fun deleteItem(position: Int) {
        mListMapel.removeAt(position)
        notifyItemRemoved(position)
    }

    fun setCallbackPopupMenu(callbackPopupMenu: RecyclerViewPopupMenu) {
        callback = callbackPopupMenu
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        itemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.mapel_item, parent, false)
        return MyViewHolder(itemBinding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(position)

    override fun getItemCount(): Int = mListMapel.size
}