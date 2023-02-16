package com.example.executive.data.dao

import androidx.room.TypeConverter
import com.example.executive.data.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter : java.io.Serializable {


    @TypeConverter
    fun stringToMap(value: String): Map<String, Country> {
        return Gson().fromJson(value,  object : TypeToken<Map<String, Country>>() {}.type)
    }

    @TypeConverter
    fun mapToString(value: Map<String, Country>?): String {
        return if(value == null) "" else Gson().toJson(value)
    }
    @TypeConverter
    fun fromCountry(country: Country): String {
        return Gson().toJson(country)
    }

    @TypeConverter
    fun toCountry(str: String): Country {
        return Gson().fromJson(str, Country::class.java)
    }

}