package com.example.studentmanagementoffline.app.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.studentmanagementoffline.R
import com.example.studentmanagementoffline.app.adapter.FragmentClassAdapter
import com.example.studentmanagementoffline.app.di.Injector
import com.example.studentmanagementoffline.app.kelas.ViewModelFactoryKelas
import com.example.studentmanagementoffline.app.kelas.ViewModelKelas
import com.example.studentmanagementoffline.app.utils.RecyclerViewItemTouchHelper
import com.example.studentmanagementoffline.app.utils.RecyclerViewPopupMenu
import com.example.studentmanagementoffline.databinding.FragmentClassBinding
import javax.inject.Inject

class ClassFragment : Fragment(), RecyclerViewPopupMenu {

    private val TAG = "ClassFragment"

    private lateinit var classBinding: FragmentClassBinding

    @Inject
    lateinit var factoryKelas: ViewModelFactoryKelas
    private val viewModelKelas: ViewModelKelas by activityViewModels {
        factoryKelas
    }

    private lateinit var adapter: FragmentClassAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as Injector).createSubComponentApp().inject(this)
        Log.d(TAG, viewModelKelas.toString())
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        classBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_class, container, false)
        return classBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = FragmentClassAdapter(callback)
        classBinding.fabClass.setOnClickListener {
            val action = ClassFragmentDirections.actionClassFragmentToAddClassFragment()
            it.findNavController().navigate(action)
        }
        viewModelKelas.getAllKelas().observe(viewLifecycleOwner) { listKelas ->
            if (listKelas.isEmpty()) {
                classBinding.classContent.recyclerviewListClass.visibility = View.GONE
            } else {
                classBinding.classContent.tvEmptyClass.visibility = View.GONE
                val itemTouchListener = ItemTouchHelper(
                    RecyclerViewItemTouchHelper(
                        lifecycleOwner = viewLifecycleOwner,
                        viewModel = viewModelKelas,
                        adapter = adapter,
                        list = listKelas
                    )
                )
                itemTouchListener.attachToRecyclerView(classBinding.classContent.recyclerviewListClass)
                viewModelKelas.getStudentTotalByClass()
                    .observe(viewLifecycleOwner) { listStudentTotal ->
                        adapter.setList(listKelas, listStudentTotal)
                        adapter.setCallbackPopupMenu(this)
                        classBinding.classContent.recyclerviewListClass.adapter = adapter
                    }
            }
        }
    }

    private val callback = object : FragmentClassAdapter.RecyclerViewClickItem {
        override fun onClickedItem(view: View, className: String) {
            val action = ClassFragmentDirections.actionListStudentByClass(className)
            view.findNavController().navigate(action)
        }
    }

    override fun updateSelectedItem(view: View, model: Any) {
        /* TODO Update class */
        val action = ClassFragmentDirections.actionClassFragmentToAddClassFragment()
        view.findNavController().navigate(action)
    }
}