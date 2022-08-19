package com.ersubhadip.liveedgedetectionsdk.fragments

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ersubhadip.liveedgedetectionsdk.R
import com.ersubhadip.liveedgedetectionsdk.databinding.FragmentResultBinding
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSobelEdgeDetectionFilter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private lateinit var dialog: Dialog
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

//        val bundle = arguments
//        if (bundle != null) {
//            val uri = bundle.getString("uri")
        val url = UrlFragment.URL
        processImage(url)
//        }

    }

    private fun processImage(uri: String?) {
        val imageUri: Uri =
            Uri.parse(uri)
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

//        saveImageToDB()
    }

//    private fun saveImageToDB() {
//        TODO("Not yet implemented")
//    }
}