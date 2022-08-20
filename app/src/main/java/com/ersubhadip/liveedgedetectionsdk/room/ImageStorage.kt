package com.ersubhadip.liveedgedetectionsdk.room

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageStorage {

    @Query("SELECT * FROM imageDetails")
    fun getAllImages(): Flow<List<ImageStorageEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeImage(imageStorageEntity: ImageStorageEntity)
}