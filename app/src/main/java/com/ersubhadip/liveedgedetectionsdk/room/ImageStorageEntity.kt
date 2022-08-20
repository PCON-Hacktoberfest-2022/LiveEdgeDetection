package com.ersubhadip.liveedgedetectionsdk.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "imageDetails")
data class ImageStorageEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "original_image") val originalImage: String?,
    @ColumnInfo(name = "processed_image") val processedImage: String?
)
