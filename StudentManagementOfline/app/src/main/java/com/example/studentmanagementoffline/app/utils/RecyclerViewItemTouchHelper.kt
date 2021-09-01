package com.example.studentmanagementoffline.app.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanagementoffline.app.adapter.*
import com.example.studentmanagementoffline.app.kelas.ViewModelKelas
import com.example.studentmanagementoffline.app.mapel.ViewModelMapel
import com.example.studentmanagementoffline.app.nilai.ViewModelNilai
import com.example.studentmanagementoffline.app.siswa.ViewModelSiswa
import com.example.studentmanagementoffline.app.tugas.ViewModelTugas
import com.example.studentmanagementoffline.data.model.*

class RecyclerViewItemTouchHelper(
    dragDirs: Int = 0,
    swipeDirs: Int = ItemTouchHelper.LEFT,
    private val viewModel: ViewModel,
    private val lifecycleOwner: LifecycleOwner,
    private val adapter: Any,
    private val list: List<Any>
) :
    ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        when (viewModel) {
            is ViewModelTugas -> {
                adapter as FragmentAssignmentAdapter
                viewModel.deleteTugas(list[viewHolder.adapterPosition] as Tugas)
                    .observe(lifecycleOwner) {
                        if (it >= 1) {
                            adapter.deleteItem(viewHolder.adapterPosition)
                        }
                    }
            }
            is ViewModelNilai -> {
                adapter as ScoreAdapter
                viewModel.deleteNilai(list[viewHolder.adapterPosition] as Nilai)
                    .observe(lifecycleOwner) {
                        if (it >= 1) {
                            adapter.deleteItem(viewHolder.adapterPosition)
                        }
                    }
            }
            is ViewModelKelas -> {
                adapter as FragmentClassAdapter
                viewModel.deleteKelas(list[viewHolder.adapterPosition] as Kelas)
                    .observe(lifecycleOwner) {
                        if (it >= 1) {
                            adapter.deleteItem(viewHolder.adapterPosition)
                        }
                    }
            }
            is ViewModelMapel -> {
                adapter as MapelAdapter
                viewModel.deleteMapel(list[viewHolder.adapterPosition] as MataPelajaran)
                    .observe(lifecycleOwner) {
                        if (it >= 1) {
                            adapter.deleteItem(viewHolder.adapterPosition)
                        }
                    }
            }
            is ViewModelSiswa -> {
                adapter as StudentAdapter
                viewModel.deleteSiswa(list[viewHolder.adapterPosition] as Siswa)
                    .observe(lifecycleOwner) {
                        if (it >= 1) {
                            adapter.deleteItem(viewHolder.adapterPosition)
                        }
                    }
            }
        }
    }
}