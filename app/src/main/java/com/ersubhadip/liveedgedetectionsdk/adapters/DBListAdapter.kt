package com.ersubhadip.liveedgedetectionsdk.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ersubhadip.liveedgedetectionsdk.R
import com.ersubhadip.liveedgedetectionsdk.models.DBListItems

class DBListAdapter(private val list: List<DBListItems>) :
    RecyclerView.Adapter<DBListAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DBListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.db_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: DBListAdapter.ViewHolder, position: Int) {
        //setting data
    }

    override fun getItemCount(): Int = list.size
}