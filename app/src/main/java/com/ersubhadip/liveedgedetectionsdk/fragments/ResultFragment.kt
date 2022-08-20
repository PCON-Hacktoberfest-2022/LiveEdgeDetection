package com.ersubhadip.liveedgedetectionsdk.fragments

import android.app.Application
import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ersubhadip.liveedgedetectionsdk.R
import com.ersubhadip.liveedgedetectionsdk.databinding.FragmentResultBinding
import com.ersubhadip.liveedgedetectionsdk.room.ImageStorageEntity
import com.ersubhadip.liveedgedetectionsdk.viewmodel.UtilViewModel
import com.ersubhadip.liveedgedetectionsdk.viewmodel.UtilsViewModelFactory
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSobelEdgeDetectionFilter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private lateinit var dialog: Dialog
    private var isDone = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(layoutInflater)
        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.process_dialog)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            when (arguments?.getInt("indexFragment")) {
                0 -> processImageByUri(arguments?.getParcelable("CamUri"))

                1 -> processImageByUri(arguments?.getParcelable("uri"))

                2 -> processImageByUrl(arguments?.getString("url"))
            }
        }

        binding.saveBtn.setOnClickListener {
            if (!isDone) {
                if (arguments != null) {
                    when (arguments?.getInt("indexFragment")) {
                        0 -> saveImageToDB(arguments?.getParcelable("CamUri"))

                        1 -> saveImageToDB(arguments?.getParcelable("uri"))

                        2 -> saveImageToDB(Uri.parse(arguments?.getString("url")))

                    }
                }
            } else {
                Toast.makeText(context, "Already Added", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun processImageByUri(uri: Uri?) {
        val gpuImageView = binding.processedImageView
        gpuImageView.setImage(uri)
        gpuImageView.filter = GPUImageSobelEdgeDetectionFilter()

        GlobalScope.launch(Dispatchers.Main) {
            dialog.show()
            delay(4000L)
            dialog.dismiss()
        }

    }

    private fun processImageByUrl(url: String?) {
        val imageUri: Uri =
            Uri.parse(url)
        val gpuImageView = binding.processedImageView
        gpuImageView.setImage(imageUri)
        gpuImageView.filter = GPUImageSobelEdgeDetectionFilter()

        GlobalScope.launch(Dispatchers.Main) {
            dialog.show()
            delay(4000L)
            dialog.dismiss()
        }
        // Later when image should be saved saved:
//        gpuImageView.saveToPictures("GPUImage", "ImageWithFilter.jpg", null)

    }

    private fun saveImageToDB(o: Uri?) {
        val viewModelFactory = UtilsViewModelFactory(requireContext())
        val dbViewmodel = ViewModelProvider(this, viewModelFactory).get(UtilViewModel::class.java)
        val data = ImageStorageEntity(0, o, o)
        dbViewmodel.addImage(data)
        Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show()
        isDone = true
    }
}