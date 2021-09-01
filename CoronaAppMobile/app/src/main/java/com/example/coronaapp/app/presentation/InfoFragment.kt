package com.example.coronaapp.app.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coronaapp.R
import com.example.coronaapp.app.adapter.InfoAdapter
import com.example.coronaapp.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    lateinit var binding : FragmentInfoBinding
    lateinit var arrayOfTitle : Array<String>
    lateinit var arrayOfDesc : Array<String>
    val adapter : InfoAdapter = InfoAdapter()
    lateinit var layoutManagerVertical : LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutManagerVertical  = LinearLayoutManager(requireContext())
        arrayOfTitle = resources.getStringArray(R.array.title_info_menu)
        arrayOfDesc = resources.getStringArray(R.array.desc_info_menu)
        adapter.setTitleAndDesc(arrayOfTitle, arrayOfDesc)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_info,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
    }

    private fun initViews() {
        binding.recyclerviewInfo.layoutManager = layoutManagerVertical
        binding.recyclerviewInfo.setHasFixedSize(true)
        binding.recyclerviewInfo.adapter = adapter
    }
}