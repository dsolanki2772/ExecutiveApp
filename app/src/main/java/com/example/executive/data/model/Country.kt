package com.example.executive.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Country(
    val country: String,
    val region: String
) {
    @PrimaryKey(autoGenerate = false)
    var countryid: Int = 0
}