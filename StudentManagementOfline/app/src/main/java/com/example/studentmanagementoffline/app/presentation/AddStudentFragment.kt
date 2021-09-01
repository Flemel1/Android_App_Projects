package com.example.studentmanagementoffline.app.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.studentmanagementoffline.R
import com.example.studentmanagementoffline.app.di.Injector
import com.example.studentmanagementoffline.app.siswa.ViewModelFactorySiswa
import com.example.studentmanagementoffline.app.siswa.ViewModelSiswa
import com.example.studentmanagementoffline.app.utils.Contract
import com.example.studentmanagementoffline.data.model.Siswa
import com.example.studentmanagementoffline.databinding.FragmentAddStudentBinding
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class AddStudentFragment : Fragment() {

    private lateinit var studentBinding: FragmentAddStudentBinding
    private var className: String? = null
    private var namaSiswa: String? = null
    private var idSiswa: Int = 0
    private val args: AddStudentFragmentArgs by navArgs()

    @Inject
    lateinit var factorySiswa: ViewModelFactorySiswa
    private val viewModelSiswa: ViewModelSiswa by activityViewModels {
        factorySiswa
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as Injector).createSubComponentApp().inject(this)
        arguments?.let { bundle ->
            className = bundle.getString(Contract.KEY_CLASS_NAME)
            namaSiswa = bundle.getString(Contract.KEY_SISWA_NAME)
            idSiswa = bundle.getInt(Contract.KEY_SISWA_ID)
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        studentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_student, container, false)
        return studentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }

    private fun init() {
        studentBinding.addStudentLayout.tvHeaderAddStudent.text = className
        if(namaSiswa != null && idSiswa != 0) {
            studentBinding.addStudentLayout.buttonAddStudent.visibility = View.GONE
            studentBinding.addStudentLayout.textfieldStudentName.editText?.setText(namaSiswa)
            studentBinding.addStudentLayout.buttonUpdateStudent.setOnClickListener {
                updateStudent()
            }
        }
        else {
            className = args.className
            studentBinding.addStudentLayout.buttonUpdateStudent.visibility = View.GONE
            studentBinding.addStudentLayout.buttonAddStudent.setOnClickListener {
                insertStudent()
            }
        }
    }

    private fun updateStudent() {
        val name =
            studentBinding.addStudentLayout.textfieldStudentName.editText?.text.toString().trim()
        if ( name == "") {
            Snackbar.make(studentBinding.root, "Field Kosong", Snackbar.LENGTH_SHORT)
                .setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                .show()
        } else {
            val siswa = Siswa(idSiswa, name, "tanggal lahir", className!!)
            viewModelSiswa.updateStudent(siswa).observe(viewLifecycleOwner) {
                if (it != -1) {
                    Snackbar.make(studentBinding.root, "Berhasil", Snackbar.LENGTH_SHORT)
                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                        .show()
                } else {
                    Snackbar.make(studentBinding.root, "Gagal", Snackbar.LENGTH_SHORT)
                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                        .show()
                }
            }
        }
    }

    private fun insertStudent() {
        val name =
            studentBinding.addStudentLayout.textfieldStudentName.editText?.text.toString().trim()
        if ( name == "") {
            Snackbar.make(studentBinding.root, "Field Kosong", Snackbar.LENGTH_SHORT)
                .setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                .show()
        } else {
            val siswa = Siswa(0, name, "tanggal lahir", className!!)
            viewModelSiswa.insertSiswa(siswa).observe(viewLifecycleOwner) {
                if (it.toInt() != -1) {
                    Snackbar.make(studentBinding.root, "Berhasil", Snackbar.LENGTH_SHORT)
                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                        .show()
                } else {
                    Snackbar.make(studentBinding.root, "Gagal", Snackbar.LENGTH_SHORT)
                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                        .show()
                }
            }
        }
    }

}