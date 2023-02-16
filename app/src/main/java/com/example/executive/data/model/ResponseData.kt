package com.example.executive.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "country_table")
data class ResponseData(
    val status: String,
    @SerializedName("status-code")
    val statusCode: String,
    val version: String,
    val access: String,
    val data: Map<String, Country>
) {
    @PrimaryKey(autoGenerate = false)
    var Id: Int = 0
}





