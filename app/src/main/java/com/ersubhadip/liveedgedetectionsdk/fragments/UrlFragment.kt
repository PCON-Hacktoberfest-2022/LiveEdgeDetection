package com.ersubhadip.liveedgedetectionsdk.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ersubhadip.liveedgedetectionsdk.databinding.FragmentUrlBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.URL
import java.net.URLConnection

class UrlFragment : Fragment() {
    private lateinit var binding: FragmentUrlBinding
    var img: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUrlBinding.inflate(layoutInflater)
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
                if (checkValidImageUrl("https://previews.123rf.com/images/artshock/artshock1209/artshock120900045/15221647-imag-of-heart-in-the-blue-sky-against-a-background-of-white-clouds-.jpg")) {
                    binding.activeBtn.visibility = View.VISIBLE
                    binding.inactiveBtn.visibility = View.GONE
                    binding.errorTv.visibility = View.INVISIBLE
                    //todo:glide

                } else {
                    binding.errorTv.visibility = View.VISIBLE
                }
            }
        }

        binding.activeBtn.setOnClickListener {
            //todo:send to process
        }

    }

    private fun checkValidImageUrl(s: String): Boolean {
        GlobalScope.launch {
            var connection: URLConnection? = null
            try {
                connection = URL(s).openConnection()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            val contentType: String = connection!!.getHeaderField("Content-Type")
            img = contentType.startsWith("image/") // if img is true then it is an image

        }
        return img
    }
}