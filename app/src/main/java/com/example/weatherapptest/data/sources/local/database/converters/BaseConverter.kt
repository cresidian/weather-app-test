package com.example.weatherapptest.data.sources.local.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

abstract class BaseConverter<T> {
    val gson = Gson()

    @TypeConverter
    fun mapListToString(value: List<T>): String {
        val type = object : TypeToken<List<T>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun mapStringToList(value: String): List<T> {
        val type = object : TypeToken<List<T>>() {}.type
        return gson.fromJson(value, type)
    }
}