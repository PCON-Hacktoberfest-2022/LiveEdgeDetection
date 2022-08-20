package com.ersubhadip.liveedgedetectionsdk.fragments

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.ersubhadip.liveedgedetectionsdk.R
import com.ersubhadip.liveedgedetectionsdk.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var frame: FrameLayout? = null

    companion object {
        var isPermissionGiven = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        frame = activity?.findViewById<FrameLayout>(R.id.frame)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    android.Manifest.permission.CAMERA
                )
            } == PackageManager.PERMISSION_GRANTED
        ) {

            isPermissionGiven = true

        } else {
            ActivityCompat.requestPermissions(
                activity as Activity,
                arrayOf(
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                8000
            )
        }

        binding.dbCard.setOnClickListener {
            changeFragment(DBListFragment())
        }

        binding.uploadCard.setOnClickListener {
            changeFragment(UploadFragment())
        }

        binding.urlCard.setOnClickListener {
            changeFragment(UrlFragment())
        }

        binding.cameraCard.setOnClickListener {
            if (isPermissionGiven) {

                changeFragment(CameraFragment())
            } else {
                Toast.makeText(
                    context,
                    "You Cannot use the feature without giving camera permission",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(frame!!.id, fragment)
        transaction?.addToBackStack("tag")
        transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction?.commit()
    }
}