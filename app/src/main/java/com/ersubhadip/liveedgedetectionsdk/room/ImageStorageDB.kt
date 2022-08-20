package com.ersubhadip.liveedgedetectionsdk.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [ImageStorageDB::class], version = 1, exportSchema = false)
abstract class ImageStorageDB : RoomDatabase() {
    abstract fun imagesDao(): ImageStorage

    companion object {
        @Volatile
        private var INSTANCE: ImageStorageDB? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): ImageStorageDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ImageStorageDB::class.java,
                    "image_db"
                ).build()
                INSTANCE = instance
                return instance
            }

        }

    }
}