package com.example.studentmanagementoffline.app.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.studentmanagementoffline.R
import com.example.studentmanagementoffline.app.adapter.StudentAdapter
import com.example.studentmanagementoffline.app.di.Injector
import com.example.studentmanagementoffline.app.nilai.ViewModelFactoryNilai
import com.example.studentmanagementoffline.app.nilai.ViewModelNilai
import com.example.studentmanagementoffline.app.siswa.ViewModelFactorySiswa
import com.example.studentmanagementoffline.app.siswa.ViewModelSiswa
import com.example.studentmanagementoffline.app.utils.Contract.Companion.KEY_CLASS_NAME
import com.example.studentmanagementoffline.app.utils.Contract.Companion.KEY_SISWA_ID
import com.example.studentmanagementoffline.app.utils.Contract.Companion.KEY_SISWA_NAME
import com.example.studentmanagementoffline.app.utils.RecyclerViewItemTouchHelper
import com.example.studentmanagementoffline.app.utils.RecyclerViewPopupMenu
import com.example.studentmanagementoffline.data.model.Siswa
import com.example.studentmanagementoffline.databinding.FragmentListStudentBinding
import javax.inject.Inject


class ListStudentFragment : Fragment(), RecyclerViewPopupMenu {

    private val args: ListStudentFragmentArgs by navArgs()
    private lateinit var adapter: StudentAdapter
    private lateinit var className: String

    private lateinit var listStudentBinding: FragmentListStudentBinding
    @Inject
    lateinit var factorySiswa: ViewModelFactorySiswa
    private val viewModelSiswa: ViewModelSiswa by activityViewModels {
        factorySiswa
    }
    @Inject
    lateinit var factoryNilai: ViewModelFactoryNilai
    private val viewModelNilai: ViewModelNilai by activityViewModels {
        factoryNilai
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as Injector).createSubComponentApp().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listStudentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_student, container, false)
        return listStudentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }

    private fun init() {
        className = args.className
        adapter = StudentAdapter(callback)
        listStudentBinding.listStudentContent.textHeaderClassName.text = className
        viewModelSiswa.getKelasWithAllSiswa(className).observe(viewLifecycleOwner) { lists ->
            if (lists.isEmpty()) {
                listStudentBinding.listStudentContent.recyclerviewListClass.visibility = View.GONE
            }
            else {
                listStudentBinding.listStudentContent.tvEmptyStudent.visibility = View.GONE
                val itemTouchListener = ItemTouchHelper(
                    RecyclerViewItemTouchHelper(
                        lifecycleOwner = viewLifecycleOwner,
                        viewModel = viewModelSiswa,
                        adapter = adapter,
                        list = lists
                    )
                )
                itemTouchListener.attachToRecyclerView(listStudentBinding.listStudentContent.recyclerviewListClass)
                adapter.setCallbackPopupMenu(this)
                viewModelNilai.getAverageByStudent().observe(viewLifecycleOwner) { listAverages ->
                    adapter.setList(lists[0].siswa, listAverages)
                    listStudentBinding.listStudentContent.recyclerviewListClass.adapter = adapter
                }
            }
        }
        listStudentBinding.fabListStudent.setOnClickListener {
            val action = ListStudentFragmentDirections.actionAddStudent(className)
            it.findNavController().navigate(action)
        }
    }

    private val callback = object : StudentAdapter.RecyclerViewOnClickItem {
        override fun onClickedItem(view: View, siswa: Siswa) {
            val action = ListStudentFragmentDirections.actionListStudentFragmentToScoreFragment()
            val bundle = bundleOf(KEY_SISWA_NAME to siswa.namaSiswa, KEY_SISWA_ID to siswa.idSiswa)
            view.findNavController().navigate(action.actionId, bundle)
        }

    }

    override fun updateSelectedItem(view: View, model: Any) {
        /* TODO update student */
        val siswa = model as Siswa
        val action = ListStudentFragmentDirections.actionAddStudent("Nothing")
        val bundle = bundleOf(KEY_SISWA_NAME to siswa.namaSiswa, KEY_SISWA_ID to siswa.idSiswa, KEY_CLASS_NAME to className)
        view.findNavController().navigate(action.actionId,bundle)
    }
}