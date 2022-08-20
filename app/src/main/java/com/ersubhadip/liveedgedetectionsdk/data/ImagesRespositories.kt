package com.ersubhadip.liveedgedetectionsdk.data

import androidx.lifecycle.LiveData
import com.ersubhadip.liveedgedetectionsdk.models.DBListItems
import com.ersubhadip.liveedgedetectionsdk.room.ImageStorage

class ImagesRespositories(private val imageStorage: ImageStorage) {

    val readAllData: LiveData<MutableList<DBListItems>> = imageStorage.getAllImages()

    suspend fun addImage(dbListItems: DBListItems) {
        imageStorage.storeImage()
    }
}