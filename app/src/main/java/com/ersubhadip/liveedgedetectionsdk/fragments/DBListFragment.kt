package com.ersubhadip.liveedgedetectionsdk.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ersubhadip.liveedgedetectionsdk.adapters.DBListAdapter
import com.ersubhadip.liveedgedetectionsdk.databinding.FragmentDBListBinding
import com.ersubhadip.liveedgedetectionsdk.models.DBListItems
import com.ersubhadip.liveedgedetectionsdk.viewmodel.UtilViewModel
import com.ersubhadip.liveedgedetectionsdk.viewmodel.UtilsViewModelFactory


class DBListFragment : Fragment() {
    private lateinit var binding: FragmentDBListBinding
    private var list: MutableList<DBListItems> = mutableListOf()
    private var dbAdapter: DBListAdapter? = null
    private lateinit var dbViewModel: UtilViewModel
    private val manager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDBListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = UtilsViewModelFactory(requireContext())
        dbViewModel = ViewModelProvider(this, factory)[UtilViewModel::class.java]
        setRv(list)
        //fetch the items and notify
        fetchDBData()
        dbAdapter?.notifyDataSetChanged()
    }

    private fun fetchDBData() {
        binding.loader.visibility = View.VISIBLE
        dbViewModel.readAllImages.observe(viewLifecycleOwner, Observer {
            for (i in it) {
                list.add(DBListItems(i.originalImage, i.processedImage))
            }
            if (list.isEmpty()) {
                binding.noDataView.visibility = View.VISIBLE
                binding.listView.visibility = View.GONE
            } else {
                binding.noDataView.visibility = View.GONE
                binding.listView.visibility = View.VISIBLE
            }
            binding.loader.visibility = View.GONE
        })
    }

    private fun setRv(list: MutableList<DBListItems>) {
        dbAdapter = DBListAdapter(context,list)
        binding.listView.apply {
            layoutManager = manager
            adapter = dbAdapter
        }
    }
}