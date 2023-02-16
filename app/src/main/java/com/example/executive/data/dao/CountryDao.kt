package com.example.executive.data.dao

import androidx.room.*
import com.example.executive.data.model.Country
import com.example.executive.data.model.ResponseData

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: ResponseData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(user: ResponseData)

    @Update
    fun update(user: ResponseData)

    @Delete
    fun delete(user: ResponseData)

    @Query("select * from country_table")
    fun getAllData(): ResponseData


}