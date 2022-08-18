package com.ersubhadip.liveedgedetectionsdk.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ImageStorage {

    @Query("")
    fun getAllImages()


    @Insert
    fun storeImage()
}