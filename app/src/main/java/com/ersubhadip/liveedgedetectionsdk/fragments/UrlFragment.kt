package com.ersubhadip.liveedgedetectionsdk.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.ersubhadip.liveedgedetectionsdk.R
import com.ersubhadip.liveedgedetectionsdk.databinding.FragmentUrlBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UrlFragment : Fragment() {
    private lateinit var binding: FragmentUrlBinding
    private var img: Boolean = false
    private var frame: FrameLayout? = null

    companion object {
        var url: String? = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUrlBinding.inflate(layoutInflater)
        frame = activity?.findViewById<FrameLayout>(R.id.frame)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.url.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    binding.activeBtn.visibility = View.GONE
                    binding.inactiveBtn.visibility = View.VISIBLE
                }
            }
        })

        binding.previewBtn.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
//                if (checkValidImageUrl(binding.url.toString().trim())) {
                if (true) {
                    binding.activeBtn.visibility = View.VISIBLE
                    binding.inactiveBtn.visibility = View.GONE
                    binding.errorTv.visibility = View.INVISIBLE
                    binding.imagePrev.alpha = 1f
                    Glide.with(requireContext()).load(binding.url.text.toString().trim())
                        .into(binding.imagePrev)

                } else {
                    binding.errorTv.visibility = View.VISIBLE
                }
            }
        }

        binding.activeBtn.setOnClickListener {
            url = binding.url.text.toString().trim()
            changeFragment(ResultFragment())
        }

    }

//    private fun checkValidImageUrl(s: String): Boolean {
//        GlobalScope.launch {
//            var connection: URLConnection? = null
//            try {
//                connection = URL(s).openConnection()
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//            val contentType: String = connection!!.getHeaderField("Content-Type")
//            img = contentType.startsWith("image/") // if img is true then it is an image
//
//        }
//        return img
//    }

    private fun changeFragment(fragment: Fragment) {
        val bundle = Bundle()
        bundle.putInt("indexFragment", 2)
        bundle.putString("url", binding.url.text.toString().trim())
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        fragment.arguments = bundle
        transaction?.replace(frame!!.id, fragment)
        transaction?.addToBackStack("tag")
        transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction?.commit()
    }
}