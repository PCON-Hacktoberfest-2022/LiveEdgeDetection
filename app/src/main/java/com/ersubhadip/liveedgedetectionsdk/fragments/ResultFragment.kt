package com.ersubhadip.liveedgedetectionsdk.fragments

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
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSobelEdgeDetectionFilter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private lateinit var dialog: Dialog
    private var isDone = false
    private var uri: Uri? = null
    private var url: String? = null
    private lateinit var dbViewmodel: UtilViewModel
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
        dbViewmodel = ViewModelProvider(this)[UtilViewModel::class.java]
//        if (arguments != null) {
//            val index = arguments?.getInt("indexFragment")
//            Toast.makeText(context, ""+index, Toast.LENGTH_SHORT).show()
//            when (index) {
//                0 -> processImageByCameraCapture()
//
//                1 -> processImageByUri(arguments?.getParcelable("uri"))
//
//                2 -> processImageByUrl(arguments?.getString("url"))
//            }
//        }

        uri = UploadFragment.uri
        url = UrlFragment.url
        if (url != null && uri == null) {
            processImageByUrl(url)
        } else if (url == null && uri != null) {
            processImageByUri(uri)
        }

        binding.saveBtn.setOnClickListener {
            if (!isDone) {
//            saveImageToDB()
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

        // Later when image should be saved saved:
//        gpuImageView.saveToPictures("GPUImage", "ImageWithFilter.jpg", null)

    }

    private fun processImageByCameraCapture() {
//        val gpuImageView = binding.processedImageView
//        gpuImageView.setImage(uri)
//        gpuImageView.filter = GPUImageSobelEdgeDetectionFilter()
//
//        GlobalScope.launch(Dispatchers.Main) {
//            dialog.show()
//            delay(4000L)
//            dialog.dismiss()
//        }
//
//        // Later when image should be saved saved:
////        gpuImageView.saveToPictures("GPUImage", "ImageWithFilter.jpg", null)

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

    private fun saveImageToDB(o: Uri?, p: Uri?) {
        val data = ImageStorageEntity(0, o, p)
        dbViewmodel.addImage(data)
        Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show()
        isDone = true

    }
}