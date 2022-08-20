package com.ersubhadip.liveedgedetectionsdk.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ersubhadip.liveedgedetectionsdk.data.ImagesRespositories
import com.ersubhadip.liveedgedetectionsdk.models.DBListItems
import com.ersubhadip.liveedgedetectionsdk.room.ImageStorageDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UtilViewModel(application: Application) : AndroidViewModel(application) {
    private val readImages: LiveData<MutableList<DBListItems>>

    private val respositories: ImagesRespositories

    init {
        val imageStorage = ImageStorageDB.getDatabase(application).imagesDao()
        respositories = ImagesRespositories(imageStorage)
        readImages = respositories.readAllData

    }

    fun addImage(dbListItems: DBListItems) {
        viewModelScope.launch(Dispatchers.IO) {
            respositories.addImage(dbListItems)
        }
    }

}