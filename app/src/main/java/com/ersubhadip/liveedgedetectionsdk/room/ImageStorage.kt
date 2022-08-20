package com.ersubhadip.liveedgedetectionsdk.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ersubhadip.liveedgedetectionsdk.models.DBListItems

@Dao
interface ImageStorage {

    @Query("SELECT * FROM imageDetails")
    fun getAllImages(): LiveData<MutableList<DBListItems>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeImage()
}