package com.example.studentmanagementoffline.app.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.studentmanagementoffline.R
import com.example.studentmanagementoffline.app.adapter.MapelAdapter
import com.example.studentmanagementoffline.app.di.Injector
import com.example.studentmanagementoffline.app.mapel.ViewModelFactoryMapel
import com.example.studentmanagementoffline.app.mapel.ViewModelMapel
import com.example.studentmanagementoffline.app.utils.Contract.Companion.KEY_MAPEL_ID
import com.example.studentmanagementoffline.app.utils.Contract.Companion.KEY_MAPEL_NAME
import com.example.studentmanagementoffline.app.utils.RecyclerViewItemTouchHelper
import com.example.studentmanagementoffline.app.utils.RecyclerViewPopupMenu
import com.example.studentmanagementoffline.data.model.MataPelajaran
import com.example.studentmanagementoffline.databinding.FragmentMapelBinding
import javax.inject.Inject

class MapelFragment : Fragment(), RecyclerViewPopupMenu {

    private val TAG = "MapelFragment"
    private lateinit var mapelBinding: FragmentMapelBinding

    @Inject
    lateinit var factoryMapel: ViewModelFactoryMapel
    private val viewModelMapel: ViewModelMapel by activityViewModels {
        factoryMapel
    }
    private val adapter = MapelAdapter()
    private lateinit var listMapelTemp: List<MataPelajaran>

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as Injector).createSubComponentApp().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mapelBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mapel, container, false)
        return mapelBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }

    private fun init() {
        mapelBinding.fabMapel.setOnClickListener {
            val action = MapelFragmentDirections.actionMapelFragmentToAddMapelFragment()
            it.findNavController().navigate(action)
        }
        viewModelMapel.getAllMapel().observe(viewLifecycleOwner) { listMapel ->
            listMapelTemp = listMapel
            if (listMapel.isEmpty()) {
                mapelBinding.mapelContent.recyclerviewListMapel.visibility = View.GONE
                mapelBinding.mapelContent.tvEmptyMapel.visibility = View.VISIBLE
            } else {
                mapelBinding.mapelContent.tvEmptyMapel.visibility = View.GONE
                val itemTouchListener = ItemTouchHelper(
                    RecyclerViewItemTouchHelper(
                        lifecycleOwner = viewLifecycleOwner,
                        viewModel = viewModelMapel,
                        adapter = adapter,
                        list = listMapel
                    )
                )
                itemTouchListener.attachToRecyclerView(mapelBinding.mapelContent.recyclerviewListMapel)
                adapter.setList(listMapel)
                adapter.setCallbackPopupMenu(this)
                mapelBinding.mapelContent.recyclerviewListMapel.adapter = adapter
            }
        }
    }

    override fun updateSelectedItem(view: View, model: Any) {
        val mapel = model as MataPelajaran
        val bundle = bundleOf(
            KEY_MAPEL_ID to mapel.mapelID,
            KEY_MAPEL_NAME to mapel.namaMapel
        )
        view.findNavController().navigate(R.id.action_mapelFragment_to_addMapelFragment, bundle)
    }
}