package com.ersubhadip.liveedgedetectionsdk.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageStorageEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "original_image") val originalImage: String?,
    @ColumnInfo(name = "processed_image") val processedImage: String?
)
