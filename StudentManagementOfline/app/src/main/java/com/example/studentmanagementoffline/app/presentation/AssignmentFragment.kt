package com.example.studentmanagementoffline.app.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.studentmanagementoffline.R
import com.example.studentmanagementoffline.app.adapter.FragmentAssignmentAdapter
import com.example.studentmanagementoffline.app.di.Injector
import com.example.studentmanagementoffline.app.tugas.ViewModelFactoryTugas
import com.example.studentmanagementoffline.app.tugas.ViewModelTugas
import com.example.studentmanagementoffline.app.utils.Contract.Companion.KEY_MAPEL_ID
import com.example.studentmanagementoffline.app.utils.Contract.Companion.KEY_TUGAS_CLASS
import com.example.studentmanagementoffline.app.utils.Contract.Companion.KEY_TUGAS_DEADLINE
import com.example.studentmanagementoffline.app.utils.Contract.Companion.KEY_TUGAS_DESC
import com.example.studentmanagementoffline.app.utils.Contract.Companion.KEY_TUGAS_ID
import com.example.studentmanagementoffline.app.utils.RecyclerViewItemTouchHelper
import com.example.studentmanagementoffline.app.utils.RecyclerViewPopupMenu
import com.example.studentmanagementoffline.data.model.Tugas
import com.example.studentmanagementoffline.databinding.FragmentAssignmentBinding
import javax.inject.Inject

class AssignmentFragment : Fragment(), RecyclerViewPopupMenu {

    private val TAG = "AssignmentFragment"
    private lateinit var assignmentFragmentBinding: FragmentAssignmentBinding
    private lateinit var adapter: FragmentAssignmentAdapter

    @Inject
    lateinit var factoryTugas: ViewModelFactoryTugas
    private val viewModelTugas: ViewModelTugas by activityViewModels {
        factoryTugas
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as Injector).createSubComponentApp().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        assignmentFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_assignment, container, false)
        return assignmentFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = FragmentAssignmentAdapter()
        init()
    }

    private fun init() {
        assignmentFragmentBinding.fabAssignment.setOnClickListener {
            val action =
                AssignmentFragmentDirections.actionAssignmentFragmentToAddAssignmentFragment()
            it.findNavController().navigate(action)
        }
        viewModelTugas.getAllTugas().observe(viewLifecycleOwner) { listTugas ->
            Log.d(TAG, "getAllTugas")
            if (listTugas.isEmpty()) {
                assignmentFragmentBinding.assignmentContent.recyclerviewAssignmentClass.visibility =
                    View.GONE
            } else {
                assignmentFragmentBinding.assignmentContent.tvEmptyAssignment.visibility = View.GONE
                adapter.setLists(listTugas)
                adapter.setCallbackPopupMenu(this)
                val itemTouchListener = ItemTouchHelper(
                    RecyclerViewItemTouchHelper(
                        lifecycleOwner = viewLifecycleOwner,
                        viewModel = viewModelTugas,
                        adapter = adapter,
                        list = listTugas
                    )
                )
                itemTouchListener.attachToRecyclerView(assignmentFragmentBinding.assignmentContent.recyclerviewAssignmentClass)
                assignmentFragmentBinding.assignmentContent.recyclerviewAssignmentClass.adapter =
                    adapter
            }
        }
    }

    override fun updateSelectedItem(view: View, model: Any) {
        val tugas = model as Tugas
        val bundle = bundleOf(
            KEY_TUGAS_ID to tugas.id,
            KEY_MAPEL_ID to tugas.mapelID,
            KEY_TUGAS_DESC to tugas.deskripsiTugas,
            KEY_TUGAS_CLASS to tugas.namaKelas,
            KEY_TUGAS_DEADLINE to tugas.deadlineTugas
        )
        view.findNavController()
            .navigate(R.id.action_assignmentFragment_to_addAssignmentFragment, bundle)
    }
}