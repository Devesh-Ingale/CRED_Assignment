package dev.devlopment.cred_assignment.ViewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.devlopment.cred_assignment.API.ApiResponse
import dev.devlopment.cred_assignment.API.ApiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoanViewModel() : ViewModel() {
    private val repository = Repository()
    private val _apiState = MutableStateFlow<ApiState>(ApiState.Loading)
    val apiState: StateFlow<ApiState> = _apiState.asStateFlow()

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            _apiState.value = ApiState.Loading
            repository.fetchData()
                .onSuccess { response ->
                    _apiState.value = ApiState.Success(response)
                }
                .onFailure { exception ->
                    _apiState.value = ApiState.Error(exception.message ?: "Unknown error occurred")
                }
        }
    }


}
