package com.example.executive.data.api

import com.example.executive.data.model.ResponseData
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {
    @GET("countries")
    suspend fun getCountries(): ResponseData
}