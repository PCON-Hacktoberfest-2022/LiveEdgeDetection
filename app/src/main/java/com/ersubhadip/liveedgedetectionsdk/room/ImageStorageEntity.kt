package com.ersubhadip.liveedgedetectionsdk.room

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "imageDetails")
data class ImageStorageEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "original_image") val originalImage: Uri?,
    @ColumnInfo(name = "processed_image") val processedImage: Uri?

)