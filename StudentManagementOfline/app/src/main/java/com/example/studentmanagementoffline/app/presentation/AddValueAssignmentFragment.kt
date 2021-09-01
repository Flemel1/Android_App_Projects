package com.example.studentmanagementoffline.app.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.studentmanagementoffline.R
import com.example.studentmanagementoffline.app.di.Injector
import com.example.studentmanagementoffline.app.nilai.ViewModelFactoryNilai
import com.example.studentmanagementoffline.app.nilai.ViewModelNilai
import com.example.studentmanagementoffline.app.tugas.ViewModelFactoryTugas
import com.example.studentmanagementoffline.app.tugas.ViewModelTugas
import com.example.studentmanagementoffline.app.utils.Contract.Companion.KEY_SISWA_ID
import com.example.studentmanagementoffline.app.utils.Contract.Companion.KEY_SISWA_NAME
import com.example.studentmanagementoffline.data.model.Nilai
import com.example.studentmanagementoffline.databinding.FragmentAddValueAssignmentBinding
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class AddValueAssignmentFragment : Fragment() {

    private lateinit var binding: FragmentAddValueAssignmentBinding
    private var mapelID: String? = null
    private var argsNamaSiswa: String? = null
    private var argsSiswaID = 0
    private var tugasID = 0

    @Inject
    lateinit var factoryNilai: ViewModelFactoryNilai

    @Inject
    lateinit var factoryTugas: ViewModelFactoryTugas

    private val viewModelNilai: ViewModelNilai by activityViewModels {
        factoryNilai
    }
    private val viewModelTugas: ViewModelTugas by activityViewModels {
        factoryTugas
    }
    private val listNamaTugas = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as Injector).createSubComponentApp().inject(this)
        arguments?.let {
            argsNamaSiswa = it.getString(KEY_SISWA_NAME)
            argsSiswaID = it.getInt(KEY_SISWA_ID)
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_value_assignment,
            container,
            false
        )
        binding.addValueContent.listener = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }

    private fun init() {
        binding.addValueContent.tvHeaderSiswa.text = argsNamaSiswa
        viewModelTugas.getAllTugas().observe(viewLifecycleOwner) { listTugas ->
            for (tugas in listTugas) {
                listNamaTugas.add(tugas.namaTugas)
            }
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                listNamaTugas
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.addValueContent.assignmentSpinner.adapter = adapter
            binding.addValueContent.assignmentSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        mapelID = listTugas[position].mapelID
                        tugasID = listTugas[position].id
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                }
        }
    }

    fun insertNilai() {
        val scoreAssignment =
            binding.addValueContent.textfieldValueForAssignment.editText?.text.toString().trim()
        if (scoreAssignment == "") {
            Snackbar.make(binding.root, "Field Kosong", Snackbar.LENGTH_SHORT)
                .setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                .show()
        } else if (scoreAssignment.toInt() > 100 || scoreAssignment.toInt() < 0) {
            Snackbar.make(binding.root, "Skala nilai 0-100", Snackbar.LENGTH_SHORT)
                .setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                .show()
        } else {
            val nilai = Nilai(0, scoreAssignment.toInt(), argsSiswaID, mapelID!!, tugasID)
            viewModelNilai.insertNilai(nilai).observe(viewLifecycleOwner) { response ->
                if (response.toInt() != -1) {
                    Snackbar.make(binding.root, "Berhasil", Snackbar.LENGTH_SHORT)
                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                        .show()
                } else {
                    Snackbar.make(binding.root, "Gagal", Snackbar.LENGTH_SHORT)
                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                        .show()
                }
            }
        }
    }
}