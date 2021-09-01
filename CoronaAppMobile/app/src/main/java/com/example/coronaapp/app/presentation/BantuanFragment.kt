package com.example.coronaapp.app.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coronaapp.R
import com.example.coronaapp.app.adapter.BantuanAdapter
import com.example.coronaapp.databinding.FragmentBantuanBinding

class BantuanFragment : Fragment() {

    lateinit var binding : FragmentBantuanBinding
    lateinit var titles : Array<String>
    val adapter : BantuanAdapter = BantuanAdapter()
    lateinit var layoutManager : LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutManager = LinearLayoutManager(requireContext())
        titles = resources.getStringArray(R.array.title_bantuan_menu)
        adapter.setTitleAndDesc(titles)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_bantuan,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
    }

    private fun initViews() {
        binding.recyclerviewBantuan.layoutManager = layoutManager
        binding.recyclerviewBantuan.setHasFixedSize(true)
        binding.recyclerviewBantuan.adapter = adapter
    }
}