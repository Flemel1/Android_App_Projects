package com.example.studentmanagementoffline.app.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanagementoffline.R
import com.example.studentmanagementoffline.app.adapter.AssignmentHomeAdapter
import com.example.studentmanagementoffline.app.adapter.ClassHomeAdapter
import com.example.studentmanagementoffline.app.di.Injector
import com.example.studentmanagementoffline.app.kelas.ViewModelFactoryKelas
import com.example.studentmanagementoffline.app.kelas.ViewModelKelas
import com.example.studentmanagementoffline.app.tugas.ViewModelFactoryTugas
import com.example.studentmanagementoffline.app.tugas.ViewModelTugas
import com.example.studentmanagementoffline.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment : Fragment() {

    private val TAG = "HomeFragment"

    private lateinit var homeBinding: FragmentHomeBinding

    @Inject
    lateinit var factoryKelas: ViewModelFactoryKelas
    @Inject
    lateinit var factoryTugas: ViewModelFactoryTugas

    private val viewModelKelas: ViewModelKelas by activityViewModels {
        factoryKelas
    }
    private val viewModelTugas: ViewModelTugas by activityViewModels {
        factoryTugas
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as Injector).createSubComponentApp().inject(this)
        Log.d(TAG, viewModelKelas.toString())
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapterHome = ClassHomeAdapter()
        val adapterAssignment = AssignmentHomeAdapter()
        viewModelKelas.getAllKelas().observe(viewLifecycleOwner) { listKelas ->
            if(listKelas.isEmpty()) {
                homeBinding.homeContent.recyclerviewClass.visibility = View.GONE
            }
            else {
                homeBinding.homeContent.tvEmptyHomeClass.visibility = View.GONE
                adapterHome.setList(listKelas)
                homeBinding.homeContent.recyclerviewClass.adapter = adapterHome
            }
        }
        viewModelTugas.getAllTugas().observe(viewLifecycleOwner) { listTugas ->
            if (listTugas.isEmpty()) {
                homeBinding.homeContent.recyclerviewAssignment.visibility = View.GONE
            }
            else {
                homeBinding.homeContent.tvEmptyHomeAssignment.visibility = View.GONE
                adapterAssignment.setList(listTugas)
                homeBinding.homeContent.recyclerviewAssignment.adapter = adapterAssignment
            }
        }
    }

}