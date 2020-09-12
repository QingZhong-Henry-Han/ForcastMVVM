package com.henryhans.forcastmvvm.internal

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class StringListToGsonConverter {

    @TypeConverter
    fun restoreStringList(listOfString: String?): List<String?>? {
        return Gson().fromJson(listOfString, object : TypeToken<List<String?>?>() {}.getType())
    }

    @TypeConverter
    fun saveStringList(listOfString: List<String?>?): String? {
        return Gson().toJson(listOfString)
    }

}
