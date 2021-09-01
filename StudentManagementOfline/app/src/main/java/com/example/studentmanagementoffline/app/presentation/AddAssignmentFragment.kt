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
import com.example.studentmanagementoffline.app.kelas.ViewModelFactoryKelas
import com.example.studentmanagementoffline.app.kelas.ViewModelKelas
import com.example.studentmanagementoffline.app.mapel.ViewModelFactoryMapel
import com.example.studentmanagementoffline.app.mapel.ViewModelMapel
import com.example.studentmanagementoffline.app.tugas.ViewModelFactoryTugas
import com.example.studentmanagementoffline.app.tugas.ViewModelTugas
import com.example.studentmanagementoffline.app.utils.Contract.Companion.KEY_MAPEL_ID
import com.example.studentmanagementoffline.app.utils.Contract.Companion.KEY_TUGAS_CLASS
import com.example.studentmanagementoffline.app.utils.Contract.Companion.KEY_TUGAS_DEADLINE
import com.example.studentmanagementoffline.app.utils.Contract.Companion.KEY_TUGAS_DESC
import com.example.studentmanagementoffline.app.utils.Contract.Companion.KEY_TUGAS_ID
import com.example.studentmanagementoffline.data.model.Tugas
import com.example.studentmanagementoffline.databinding.FragmentAddAssignmentBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class AddAssignmentFragment : Fragment() {

    private val TAG = "AddAssignmentFragment"

    private lateinit var addAssignmentBinding: FragmentAddAssignmentBinding
    private var listMapel = ArrayList<String>()
    private var listKelas = ArrayList<String>()
    private lateinit var selectedKelas: String
    private lateinit var mapelID: String
    private var deadlineAssignment = "tanggal kosong"
    private var argsTugasID: Int? = null
    private var argsMapelID: String? = null
    private var argsTugasDeadline: String? = null
    private var argsTugasClass: String? = null
    private var argsTugasDesc: String? = null

    @Inject
    lateinit var factory: ViewModelFactoryKelas

    @Inject
    lateinit var factoryMapel: ViewModelFactoryMapel

    @Inject
    lateinit var factoryTugas: ViewModelFactoryTugas
    private val viewModelKelas: ViewModelKelas by activityViewModels {
        factory
    }
    private val viewModelMapel: ViewModelMapel by activityViewModels {
        factoryMapel
    }
    private val viewModelTugas: ViewModelTugas by activityViewModels {
        factoryTugas
    }

    private val datePicker = MaterialDatePicker.Builder.datePicker()
        .setSelection(MaterialDatePicker.todayInUtcMilliseconds()).build()

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as Injector).createSubComponentApp().inject(this)
        arguments?.let { bundle ->
            argsTugasID = bundle.getInt(KEY_TUGAS_ID)
            argsMapelID = bundle.getString(KEY_MAPEL_ID)
            argsTugasClass = bundle.getString(KEY_TUGAS_CLASS)
            argsTugasDeadline = bundle.getString(KEY_TUGAS_DEADLINE)
            argsTugasDesc = bundle.getString(KEY_TUGAS_DESC)
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        addAssignmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_assignment, container, false)
        addAssignmentBinding.addAssignmentContent.listener = this
        return addAssignmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }

    private fun init() {
        addAssignmentBinding.addAssignmentContent.buttonUpdateAssignment.visibility = View.GONE
        if (argsTugasID != null && argsMapelID != null && argsTugasClass != null && argsTugasDeadline != null && argsTugasDesc != null) {
            addAssignmentBinding.addAssignmentContent.tvDate.text = argsTugasDeadline
            addAssignmentBinding.addAssignmentContent.textfieldDetailAssignment.editText?.setText(
                argsTugasDesc
            )
            addAssignmentBinding.addAssignmentContent.classNameSpinner.prompt = argsTugasClass
            addAssignmentBinding.addAssignmentContent.buttonUpdateAssignment.visibility =
                View.VISIBLE
            addAssignmentBinding.addAssignmentContent.buttonAddAssignment.visibility = View.GONE
        }
        viewModelKelas.getAllKelas().observe(viewLifecycleOwner) { list ->
            for (element in list) {
                listKelas.add(element.namaKelas)
            }
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                listKelas
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            addAssignmentBinding.addAssignmentContent.classNameSpinner.adapter = adapter
            addAssignmentBinding.addAssignmentContent.classNameSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        selectedKelas = listKelas[position]
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                }
        }
        viewModelMapel.getAllMapel().observe(viewLifecycleOwner) { list ->
            val mapMapel = HashMap<String, String>()
            for (mapel in list) {
                listMapel.add(mapel.namaMapel)
                mapMapel[mapel.mapelID] = mapel.namaMapel
            }
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                listMapel
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            addAssignmentBinding.addAssignmentContent.mapelNameSpinner.adapter = adapter
            addAssignmentBinding.addAssignmentContent.mapelNameSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val filteredMapelID = mapMapel.filterValues { it == listMapel[position] }
                        for (key in filteredMapelID.keys) {
                            mapelID = key
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }
                }
        }

        addAssignmentBinding.addAssignmentContent.datePicker.setOnClickListener {
            datePicker.show(parentFragmentManager, "date")
            datePicker.addOnPositiveButtonClickListener {
                deadlineAssignment = datePicker.headerText
                addAssignmentBinding.addAssignmentContent.tvDate.text = datePicker.headerText
            }
        }
    }

    fun insertAssignment() {
        val descAssignment =
            addAssignmentBinding.addAssignmentContent.textfieldDetailAssignment.editText?.text.toString()
                .trim()
        val assigmentName =
            addAssignmentBinding.addAssignmentContent.textfieldAssignmentName.editText?.text.toString()
                .trim()
        if (mapelID == "" || assigmentName == "" || selectedKelas == "" || descAssignment == "" || deadlineAssignment == "tanggal kosong") {
            Snackbar.make(addAssignmentBinding.root, "Field Kosong", Snackbar.LENGTH_SHORT)
                .setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                .show()
        } else {
            val assignment =
                Tugas(0, assigmentName, mapelID, selectedKelas, descAssignment, deadlineAssignment)
            viewModelTugas.insertTugas(assignment).observe(viewLifecycleOwner) {
                if (it.toInt() != -1) {
                    Snackbar.make(addAssignmentBinding.root, "Berhasil", Snackbar.LENGTH_SHORT)
                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                        .show()
                    addAssignmentBinding.addAssignmentContent.textfieldDetailAssignment.editText?.text =
                        null
                    addAssignmentBinding.addAssignmentContent.tvDate.text = "Tanggal Kosong"
                    deadlineAssignment = "tanggal kosong"
                } else {
                    Snackbar.make(addAssignmentBinding.root, "Gagal", Snackbar.LENGTH_SHORT)
                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                        .show()
                }
            }
        }
    }

    fun updateAssignment() {
        val descAssignment =
            addAssignmentBinding.addAssignmentContent.textfieldDetailAssignment.editText?.text.toString()
                .trim()
        val assigmentName =
            addAssignmentBinding.addAssignmentContent.textfieldAssignmentName.editText?.text.toString()
        val updateDeadlineAssignment =
            addAssignmentBinding.addAssignmentContent.tvDate.text.toString()
        if (mapelID == "" || assigmentName == "" || selectedKelas == "" || descAssignment == "") {
            Snackbar.make(addAssignmentBinding.root, "Field Kosong", Snackbar.LENGTH_SHORT)
                .setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                .show()
        } else {
            val assignment =
                Tugas(
                    argsTugasID!!,
                    assigmentName,
                    mapelID,
                    selectedKelas,
                    descAssignment,
                    updateDeadlineAssignment
                )
            viewModelTugas.updateTugas(assignment).observe(viewLifecycleOwner) {
                if (it >= -1) {
                    Snackbar.make(
                        addAssignmentBinding.root,
                        "Update Berhasil",
                        Snackbar.LENGTH_SHORT
                    )
                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                        .show()
                } else {
                    Snackbar.make(addAssignmentBinding.root, "Update Gagal", Snackbar.LENGTH_SHORT)
                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                        .show()
                }
            }
        }
    }
}