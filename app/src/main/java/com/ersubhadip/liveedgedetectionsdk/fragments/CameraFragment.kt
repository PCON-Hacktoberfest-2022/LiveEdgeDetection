package com.ersubhadip.liveedgedetectionsdk.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.ersubhadip.liveedgedetectionsdk.R
import com.ersubhadip.liveedgedetectionsdk.databinding.FragmentCameraBinding


class CameraFragment : Fragment() {
    private lateinit var binding: FragmentCameraBinding
    private var uri: Uri? = null

    //    private lateinit var outputDirectory: File
    private var imageCapture: ImageCapture? = null
    private var frame: FrameLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCameraBinding.inflate(layoutInflater)
        frame = activity?.findViewById<FrameLayout>(R.id.frame)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        outputDirectory = getOutputDirectory()
        if (HomeFragment.isPermissionGiven) {
            binding.previewView.post {
                startCamera()
            }
        }

        binding.capture.setOnClickListener {
            capture()
        }

        binding.yes.setOnClickListener {
            changeFragment(ResultFragment())
        }

        binding.no.setOnClickListener {
            uri = null
            binding.afterCaptureLayout.visibility = View.GONE
            binding.previewImage.visibility = View.GONE
            binding.previewView.visibility = View.VISIBLE
            binding.capture.visibility = View.VISIBLE
            startCamera()
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = context?.let { ProcessCameraProvider.getInstance(it) }
        cameraProviderFuture?.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build()
                .also {
                    it.setSurfaceProvider(
                        binding.previewView.surfaceProvider
                    )
                }
            imageCapture = ImageCapture.Builder().build()
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
            } catch (e: Exception) {

            }
        }, context?.let { ContextCompat.getMainExecutor(it) }!!)
//            val parent:ViewGroup = binding.texture.parent as ViewGroup
//            parent.removeView(binding.texture)
//            parent.addView(binding.texture, 0)
//            binding.texture.surfaceTexture = it.surefaceTexture

    }

    private fun capture() {
        val imageCapture = imageCapture
        val imageFile = createTempFile(System.currentTimeMillis().toString(), ".jpg")
        val outputFileOptions = ImageCapture.OutputFileOptions.Builder(imageFile).build()

        imageCapture?.takePicture(
            outputFileOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    uri = outputFileResults.savedUri
                    binding.previewView.visibility = View.GONE
                    binding.previewImage.visibility = View.VISIBLE
                    binding.capture.visibility = View.GONE
                    binding.afterCaptureLayout.visibility = View.VISIBLE
                    binding.previewImage.setImageURI(uri)
                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(context, "Something Went Wrong!", Toast.LENGTH_SHORT).show()

                }
            })

    }

    private fun changeFragment(fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(frame!!.id, fragment)
        transaction?.addToBackStack("tag")
        transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction?.commit()
    }
}