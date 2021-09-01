package com.example.studentmanagementoffline.app.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.studentmanagementoffline.R
import com.example.studentmanagementoffline.app.di.Injector
import com.example.studentmanagementoffline.app.mapel.ViewModelFactoryMapel
import com.example.studentmanagementoffline.app.mapel.ViewModelMapel
import com.example.studentmanagementoffline.app.utils.Contract.Companion.KEY_MAPEL_ID
import com.example.studentmanagementoffline.app.utils.Contract.Companion.KEY_MAPEL_NAME
import com.example.studentmanagementoffline.data.model.MataPelajaran
import com.example.studentmanagementoffline.databinding.FragmentAddMapelBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*
import javax.inject.Inject


class AddMapelFragment : Fragment() {

    private lateinit var addMapelBinding: FragmentAddMapelBinding
    private var argsMapelID: String? = null
    private var argsMapelName: String? = null

    @Inject
    lateinit var factory: ViewModelFactoryMapel
    private val viewModelMapel: ViewModelMapel by activityViewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as Injector).createSubComponentApp().inject(this)
        arguments?.let { bundle ->
            argsMapelID = bundle.getString(KEY_MAPEL_ID)
            argsMapelName = bundle.getString(KEY_MAPEL_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        addMapelBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_mapel, container, false)
        addMapelBinding.addMapelContent.listener = this
        return addMapelBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }

    private fun init() {
        if (argsMapelID != null && argsMapelName != null) {
            addMapelBinding.addMapelContent.textfieldMapelId.editText?.setText(argsMapelID)
            addMapelBinding.addMapelContent.textfieldMapelName.editText?.setText(argsMapelName)
            addMapelBinding.addMapelContent.buttonAddMapel.visibility = View.GONE
            addMapelBinding.addMapelContent.textfieldMapelId.isEnabled = false
        }
    }

    fun insertMapel() {
        val mapelID =
            addMapelBinding.addMapelContent.textfieldMapelId.editText?.text.toString().trim()
                .toUpperCase(Locale.ROOT)
        val mapelName =
            addMapelBinding.addMapelContent.textfieldMapelName.editText?.text.toString().trim()
        if (mapelID == "" || mapelName == "") {
            Snackbar.make(addMapelBinding.root, "Field Kosong", Snackbar.LENGTH_SHORT)
                .setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                .show()
        } else {
            val mapel = MataPelajaran(mapelID, mapelName)
            viewModelMapel.insertMapel(mapel).observe(viewLifecycleOwner) {
                if (it.toInt() != -1) {
                    Snackbar.make(addMapelBinding.root, "Berhasil", Snackbar.LENGTH_SHORT)
                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                        .show()
                    addMapelBinding.addMapelContent.textfieldMapelId.editText?.text = null
                    addMapelBinding.addMapelContent.textfieldMapelName.editText?.text = null
                } else {
                    Snackbar.make(
                        addMapelBinding.root,
                        "ID Sudah Ada",
                        Snackbar.LENGTH_SHORT
                    )
                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                        .show()
                }
            }
        }
    }

    fun updateMapel() {
        val mapelID =
            addMapelBinding.addMapelContent.textfieldMapelId.editText?.text.toString().trim()
                .toUpperCase(Locale.ROOT)
        val mapelName =
            addMapelBinding.addMapelContent.textfieldMapelName.editText?.text.toString().trim()
        val mapel = MataPelajaran(mapelID, mapelName)
        viewModelMapel.updateMapel(mapel).observe(viewLifecycleOwner) {
            if (it >= 1) {
                Snackbar.make(
                    addMapelBinding.root,
                    "Update Berhasil",
                    Snackbar.LENGTH_SHORT
                )
                    .setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                    .show()
            }
            else {
                Snackbar.make(
                    addMapelBinding.root,
                    "Update Gagal",
                    Snackbar.LENGTH_SHORT
                )
                    .setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                    .show()
            }
        }
    }
}