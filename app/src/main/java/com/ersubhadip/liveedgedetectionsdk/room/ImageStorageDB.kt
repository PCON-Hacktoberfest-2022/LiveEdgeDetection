package com.ersubhadip.liveedgedetectionsdk.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ImageStorageDB::class], version = 1)
abstract class ImageStorageDB : RoomDatabase() {
//    abstract fun imagesDao(): UserDao
}