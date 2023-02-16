package com.example.executive.ui.userdetails

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.executive.data.dao.CountryDao
import com.example.executive.data.db.ExecutiveDatabase
import com.example.executive.data.model.Country
import com.example.executive.data.model.ResponseData
import com.example.executive.data.repository.UserDetailsRepository
import com.example.executive.ui.base.UiState
import com.example.executive.utils.Commons
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class UserViewModel(
    private val context: Context,
    private val userDetailsRepository: UserDetailsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<ResponseData>>(UiState.Loading)

    val uiState: StateFlow<UiState<ResponseData>> = _uiState
    private lateinit var countryDao: CountryDao

    init {
        fetchUserDetails()
    }

    private fun fetchUserDetails() {
        if (Commons.checkForInternet(context)) {
            viewModelScope.launch {
                userDetailsRepository.getCountries()
                    .catch { e ->
                        _uiState.value = UiState.Error(e.toString())
                    }.collect {
                        _uiState.value = UiState.Success(it)
                    }
            }
        } else {
            GlobalScope.launch {
                val db = ExecutiveDatabase.getInstance(context)
                countryDao = db.countryDao()
                val list = countryDao.getAllData()
                _uiState.value = UiState.Success(list)
            }
        }
    }
}