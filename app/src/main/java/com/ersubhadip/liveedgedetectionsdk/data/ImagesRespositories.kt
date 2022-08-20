package com.ersubhadip.liveedgedetectionsdk.data

import androidx.lifecycle.MutableLiveData
import com.ersubhadip.liveedgedetectionsdk.room.ImageStorage
import com.ersubhadip.liveedgedetectionsdk.room.ImageStorageEntity
import kotlinx.coroutines.flow.Flow

class ImagesRespositories(private val imageStorage: ImageStorage) {

    val readAllData: Flow<List<ImageStorageEntity>> = imageStorage.getAllImages()

    suspend fun addImage(dbListItems: ImageStorageEntity) {
        imageStorage.storeImage(dbListItems)
    }
}