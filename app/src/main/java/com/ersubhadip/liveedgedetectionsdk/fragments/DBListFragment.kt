package com.ersubhadip.liveedgedetectionsdk.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ersubhadip.liveedgedetectionsdk.adapters.DBListAdapter
import com.ersubhadip.liveedgedetectionsdk.databinding.FragmentDBListBinding
import com.ersubhadip.liveedgedetectionsdk.models.DBListItems


class DBListFragment : Fragment() {
    private lateinit var binding: FragmentDBListBinding
    private var list: MutableList<DBListItems> = mutableListOf()
    private var dbAdapter: DBListAdapter? = null
    val manager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

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
        setRv(list)
        //fetch the items and notify
        fetchDBData()
        dbAdapter?.notifyDataSetChanged()
    }

    private fun fetchDBData() {

    }

    private fun setRv(list: MutableList<DBListItems>) {
        dbAdapter = DBListAdapter(list)
        binding.listView.apply {
            layoutManager = manager
            adapter = dbAdapter
        }
    }
}