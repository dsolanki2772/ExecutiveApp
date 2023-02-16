package com.example.executive.data.repository

import com.example.executive.data.api.NetworkService
import com.example.executive.data.model.Country
import com.example.executive.data.model.ResponseData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDetailsRepository @Inject constructor(private val networkService: NetworkService) {
    fun getCountries(): Flow<ResponseData> {
        return flow {
            emit(networkService.getCountries())
        }.map {
            it
        }
    }
}
