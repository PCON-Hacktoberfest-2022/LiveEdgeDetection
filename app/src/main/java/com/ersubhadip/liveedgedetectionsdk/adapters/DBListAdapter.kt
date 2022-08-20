package com.ersubhadip.liveedgedetectionsdk.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ersubhadip.liveedgedetectionsdk.R
import com.ersubhadip.liveedgedetectionsdk.models.DBListItems
import jp.co.cyberagent.android.gpuimage.GPUImageView
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSobelEdgeDetectionFilter

class DBListAdapter(private val list: MutableList<DBListItems>) :
    RecyclerView.Adapter<DBListAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image1 = view.findViewById<ImageView>(R.id.originalImage)
        val image2 = view.findViewById<GPUImageView>(R.id.processedImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DBListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.db_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: DBListAdapter.ViewHolder, position: Int) {
        //setting data
        holder.image1.setImageURI(list.get(position).original)
//        holder.image2.setImageURI(list.get(position).processed)

        val gpuImageView = holder.image2
        gpuImageView.setImage(list[position].processed)
        gpuImageView.filter = GPUImageSobelEdgeDetectionFilter()
    }

    override fun getItemCount(): Int = list.size
}