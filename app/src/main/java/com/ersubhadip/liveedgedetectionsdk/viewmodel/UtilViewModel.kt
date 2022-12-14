package com.ersubhadip.liveedgedetectionsdk.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ersubhadip.liveedgedetectionsdk.data.ImagesRespositories
import com.ersubhadip.liveedgedetectionsdk.room.ImageStorageDB
import com.ersubhadip.liveedgedetectionsdk.room.ImageStorageEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

open class UtilViewModel(myApplication: Context) : ViewModel() {
    private var readImages = MutableLiveData<List<ImageStorageEntity>>()
    val readAllImages: LiveData<List<ImageStorageEntity>>
        get() = readImages

    private val respositories: ImagesRespositories

    init {
        val imageStorage = ImageStorageDB.getDatabase(myApplication)!!.imagesDao()
        respositories = ImagesRespositories(imageStorage)
        viewModelScope.launch {
            respositories.readAllData.collectLatest {
                readImages.value = it
            }
        }
    }

    fun addImage(dbListItems: ImageStorageEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            respositories.addImage(dbListItems)
        }
    }

}