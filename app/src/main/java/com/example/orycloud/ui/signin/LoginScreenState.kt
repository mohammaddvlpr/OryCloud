package com.example.orycloud.ui.signin


data class LoginScreenState(
    val password: String = String(), val email: String = String(),
    val showSuccessToast: Boolean = false
)

