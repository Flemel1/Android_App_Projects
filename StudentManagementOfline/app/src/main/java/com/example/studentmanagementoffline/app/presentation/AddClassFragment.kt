package com.example.studentmanagementoffline.app.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.studentmanagementoffline.R
import com.example.studentmanagementoffline.app.di.Injector
import com.example.studentmanagementoffline.app.kelas.ViewModelFactoryKelas
import com.example.studentmanagementoffline.app.kelas.ViewModelKelas
import com.example.studentmanagementoffline.data.model.Kelas
import com.example.studentmanagementoffline.databinding.AddClassContentBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*
import javax.inject.Inject

class AddClassFragment : Fragment() {

    private val TAG = "AddClassFragment"

    private lateinit var binding: AddClassContentBinding
    @Inject
    lateinit var factory: ViewModelFactoryKelas
    private val viewModelKelas: ViewModelKelas by activityViewModels {
        factory
    }

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
        binding = AddClassContentBinding.inflate(inflater, container, false)
        binding.addClassFragment = this
        return binding.root
    }


    fun insertClass() {
        val className = binding.textfieldClassName.editText?.text.toString().trim()
            .toUpperCase(Locale.ROOT)
        if (className == "") {
            Snackbar.make(binding.root, "Nama Kelas Kosong", Snackbar.LENGTH_SHORT)
                .setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                .show()
        }
        else {
            viewModelKelas.insertKelas(Kelas(className)).observe(viewLifecycleOwner) {
                if (it.toInt() != -1) {
                    Snackbar.make(binding.root, "Berhasil", Snackbar.LENGTH_SHORT)
                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.black)).show()
                    binding.textfieldClassName.editText?.text = null
                } else {
                    Snackbar.make(binding.root, "Nama Kelas Sudah Ada", Snackbar.LENGTH_SHORT)
                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                        .show()
                }
            }
        }
    }
}