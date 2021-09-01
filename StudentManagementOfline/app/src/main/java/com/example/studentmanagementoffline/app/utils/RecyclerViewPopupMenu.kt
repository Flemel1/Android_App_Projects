package com.example.studentmanagementoffline.app.utils

import android.view.View
import com.example.studentmanagementoffline.data.model.MataPelajaran
import com.example.studentmanagementoffline.data.model.Tugas

interface RecyclerViewPopupMenu {
    fun updateSelectedItem(view: View, model: Any)
}