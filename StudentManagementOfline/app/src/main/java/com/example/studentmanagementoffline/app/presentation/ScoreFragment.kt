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
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.studentmanagementoffline.R
import com.example.studentmanagementoffline.app.adapter.ScoreAdapter
import com.example.studentmanagementoffline.app.di.Injector
import com.example.studentmanagementoffline.app.nilai.ViewModelFactoryNilai
import com.example.studentmanagementoffline.app.nilai.ViewModelNilai
import com.example.studentmanagementoffline.app.utils.Contract.Companion.KEY_SISWA_ID
import com.example.studentmanagementoffline.app.utils.Contract.Companion.KEY_SISWA_NAME
import com.example.studentmanagementoffline.app.utils.RecyclerViewItemTouchHelper
import com.example.studentmanagementoffline.databinding.FragmentScoreBinding
import javax.inject.Inject

class ScoreFragment : Fragment() {

    private lateinit var bindingScoreFragment: FragmentScoreBinding
    private var namaSiswa: String? = null
    private var idSiswa: Int = 0

    @Inject
    lateinit var factoryNilai: ViewModelFactoryNilai

    private val viewModelNilai: ViewModelNilai by activityViewModels {
        factoryNilai
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as Injector).createSubComponentApp().inject(this)
        arguments?.let { bundle ->
            namaSiswa = bundle.getString(KEY_SISWA_NAME)
            idSiswa = bundle.getInt(KEY_SISWA_ID)
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingScoreFragment =
            DataBindingUtil.inflate(inflater, R.layout.fragment_score, container, false)
        return bindingScoreFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }

    private fun init() {
        viewModelNilai.getNilaiByStudentID(idSiswa).observe(viewLifecycleOwner) { response ->
            if (response.isEmpty()) {
                bindingScoreFragment.scoreContent.recyclerviewListScore.visibility = View.GONE
            } else {
                bindingScoreFragment.scoreContent.tvEmptyScore.visibility = View.GONE
                val adapter = ScoreAdapter()
                adapter.setList(response)
                val itemTouchListener = ItemTouchHelper(
                    RecyclerViewItemTouchHelper(
                        lifecycleOwner = viewLifecycleOwner,
                        viewModel = viewModelNilai,
                        adapter = adapter,
                        list = response
                    )
                )
                itemTouchListener.attachToRecyclerView(bindingScoreFragment.scoreContent.recyclerviewListScore)
                bindingScoreFragment.scoreContent.recyclerviewListScore.adapter = adapter
            }
        }
        bindingScoreFragment.fabAddScore.setOnClickListener {
            val action = ScoreFragmentDirections.actionScoreFragmentToAddValueAssignmentFragment()
            val bundle = bundleOf(KEY_SISWA_NAME to namaSiswa, KEY_SISWA_ID to idSiswa)
            it.findNavController().navigate(action.actionId, bundle)
        }
    }
}