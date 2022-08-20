package com.ersubhadip.liveedgedetectionsdk.room

import android.net.Uri
import androidx.room.TypeConverter

class UriToStringConverter {
    @TypeConverter
    fun fromUriToString(value: Uri): String {
        return value.toString()
    }

    @TypeConverter
    fun dateToTimestamp(value: String): Uri {
        return Uri.parse(value)
    }
}