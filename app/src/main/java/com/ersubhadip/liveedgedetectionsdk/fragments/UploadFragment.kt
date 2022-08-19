package com.ersubhadip.liveedgedetectionsdk.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.ersubhadip.liveedgedetectionsdk.R
import com.ersubhadip.liveedgedetectionsdk.databinding.FragmentUploadBinding


class UploadFragment : Fragment() {
    private val REQUEST_CODE = 3000
    private lateinit var binding: FragmentUploadBinding
    private var isChoosen = false
    private var frame: FrameLayout? = null

    companion object {
        var uri: Uri? = null

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUploadBinding.inflate(layoutInflater)
        frame = activity?.findViewById<FrameLayout>(R.id.frame)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.uploadBtn.setOnClickListener {
            openGalleryForImage()
        }

        binding.activeBtn.setOnClickListener {
            if (isChoosen) {
                changeFragment(ResultFragment())
            } else {
                Toast.makeText(context, "Please choose an image!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            uri = data?.data
            binding.imagePrev.alpha = 1f
            binding.imagePrev.setImageURI(data?.data)
            isChoosen = true
        }
    }

    private fun changeFragment(fragment: Fragment) {
        val bundle = Bundle()
        bundle.putInt("indexFragment", 1)
        bundle.putParcelable("uri", uri)
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        val resultFragment = ResultFragment()
        resultFragment.arguments = bundle
        transaction?.replace(frame!!.id, fragment)
        transaction?.addToBackStack("tag")
        transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction?.commit()
    }
}