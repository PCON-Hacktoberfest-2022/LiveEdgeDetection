package com.ersubhadip.liveedgedetectionsdk.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UtilsViewModelFactory(private val application: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(UtilViewModel::class.java)) {
            return UtilViewModel(application) as T
        }

        throw IllegalArgumentException("Class NOT Found!")
    }
}