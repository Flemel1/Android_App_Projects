package com.example.inventorysystemapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.inventorysystemapp.R
import com.example.inventorysystemapp.utils.DataStoreUtil
import kotlinx.coroutines.launch

class LogoutFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(requireContext(), LoginActivity::class.java)
        val dataStore = DataStoreUtil(requireContext())
        lifecycleScope.launch {
            dataStore.setStatusLogin(false)
            dataStore.setStatusUser("nothing")
        }
        requireActivity().startActivity(intent)
        requireActivity().finish()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_logout, container, false)
    }
}