package com.example.coronaapp.app.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coronaapp.R
import com.example.coronaapp.app.adapter.IndonesiaCaseAdapter
import com.example.coronaapp.app.adapter.WorldCaseAdapter
import com.example.coronaapp.app.di.Injector
import com.example.coronaapp.app.indonesia.IndonesiaViewModel
import com.example.coronaapp.app.indonesia.IndonesiaViewModelFactory
import com.example.coronaapp.app.world.WorldViewModel
import com.example.coronaapp.app.world.WorldViewModelFactory
import com.example.coronaapp.data.util.LayoutManagerCustom
import com.example.coronaapp.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment : Fragment() {

    private val TAG = "HomeFragment"

    @Inject
    lateinit var factory : IndonesiaViewModelFactory
    @Inject
    lateinit var factoryWorld : WorldViewModelFactory
    private val viewModelIndonesiaCase: IndonesiaViewModel by activityViewModels{
        factory
    }
    private val viewModelWorldCase: WorldViewModel by activityViewModels{
        factoryWorld
    }
    private lateinit var binding : FragmentHomeBinding
    private lateinit var layoutManagerHorizontalIndonesia : LinearLayoutManager
    private lateinit var layoutManagerHorizontalWorld : LinearLayoutManager
    private val adapter = IndonesiaCaseAdapter()
    val adapterWorld = WorldCaseAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as Injector).createCoronaCaseSubComponent().inject(this)
        layoutManagerHorizontalIndonesia = LayoutManagerCustom(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false)
        layoutManagerHorizontalWorld = LayoutManagerCustom(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false)
        Log.d(TAG, "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
    }

    private fun initViews() {
        binding.recyclerviewIndonesiaCase.layoutManager = null
        binding.recyclerviewWorldCase.layoutManager = null
        binding.recyclerviewIndonesiaCase.layoutManager = layoutManagerHorizontalIndonesia
        binding.recyclerviewWorldCase.layoutManager = layoutManagerHorizontalWorld
        binding.recyclerviewIndonesiaCase.setHasFixedSize(true)
        binding.recyclerviewWorldCase.setHasFixedSize(true)
        viewModelIndonesiaCase.getAllIndonesiaCase().observe(viewLifecycleOwner) {
            adapter.setList(it)
            Log.d(TAG, "list size : ${it.size}")
            binding.recyclerviewIndonesiaCase.adapter = adapter
        }
        viewModelWorldCase.getWorldPositiveCase().observe(viewLifecycleOwner) { positif ->
            viewModelWorldCase.getWorldRecoverCase().observe(viewLifecycleOwner) { sembuh ->
                viewModelWorldCase.getWorldDiedCase().observe(viewLifecycleOwner) { died ->
                    adapterWorld.setList(positif, sembuh, died)
                    Log.d(TAG, positif.toString())
                    binding.recyclerviewWorldCase.adapter = adapterWorld
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }
}