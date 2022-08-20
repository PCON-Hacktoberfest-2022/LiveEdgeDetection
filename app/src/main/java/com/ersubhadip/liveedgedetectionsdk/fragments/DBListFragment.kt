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


class DBListFragment : Fragment() {
    private lateinit var binding: FragmentDBListBinding
    private var list: List<DBListItems> = mutableListOf()
    private var dbAdapter: DBListAdapter? = null
    private lateinit var dbViewModel: UtilViewModel
    private val manager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

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
        dbViewModel = ViewModelProvider(this)[UtilViewModel::class.java]
        setRv(list)
        //fetch the items and notify
        fetchDBData()
        dbAdapter?.notifyDataSetChanged()
    }

    private fun fetchDBData() {
        dbViewModel.readAllImages.observe(viewLifecycleOwner, Observer {
//            list.add(DBListItems())

        })
    }

    private fun setRv(list: List<DBListItems>) {
        dbAdapter = DBListAdapter(list)
        binding.listView.apply {
            layoutManager = manager
            adapter = dbAdapter
        }
    }
}