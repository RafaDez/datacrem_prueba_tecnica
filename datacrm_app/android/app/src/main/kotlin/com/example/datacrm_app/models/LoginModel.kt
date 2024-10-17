package com.example.datacrm_app.models

class LoginModel {
    data class ApiResponseGetKey(
        val success: Boolean,
        val result: GetKeyResult
    )

    data class GetKeyResult (
        val token: String,
        val serverTime: Long,
        val expireTime: Long
    )

    data class ApiResponseLogin(
        val success: Boolean,
        val result: LoginResult?
    )

    data class LoginResult(
        val sessionName: String,
        val userId: String,
        val version: String,
        val vtigerVersion: String
    )
}