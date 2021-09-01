package com.example.inventorysystemapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inventorysystemapp.R
import com.example.inventorysystemapp.adapter.UserAdapter
import com.example.inventorysystemapp.databinding.FragmentUserBinding
import com.example.inventorysystemapp.factory.InventoryViewModelFactory
import com.example.inventorysystemapp.repository.InventoryRepository
import com.example.inventorysystemapp.viewmodel.InventoryViewModel

class UserFragment : Fragment() {
    val TAG = "UserFragment"
    private lateinit var binding: FragmentUserBinding
    private val repository: InventoryRepository = InventoryRepository()
    private lateinit var adapter : UserAdapter

    private val inventoryViewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, inventoryViewModel.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_user, container, false)
        binding.recyclerviewUser.layoutManager = LinearLayoutManager(context)
        inventoryViewModel.myAllUser.observe(viewLifecycleOwner) { allUser ->
            Log.d(TAG, allUser.body.toString())
            adapter = UserAdapter(allUser.body)
            binding.recyclerviewUser.swapAdapter(adapter, false)
        }
        return binding.root
    }

}