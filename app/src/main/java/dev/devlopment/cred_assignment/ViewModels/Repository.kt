package dev.devlopment.cred_assignment.ViewModels

import dev.devlopment.cred_assignment.API.ApiClient
import dev.devlopment.cred_assignment.API.ApiResponse

class Repository {
    suspend fun fetchData(): Result<ApiResponse> {
        return try {
            val response = ApiClient.apiService.getData()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("API call failed with ${response.code()}: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}