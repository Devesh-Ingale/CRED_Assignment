package dev.devlopment.cred_assignment.API

sealed class ApiState {
    object Loading : ApiState()
    data class Success(val data: ApiResponse) : ApiState()
    data class Error(val message: String) : ApiState()
}