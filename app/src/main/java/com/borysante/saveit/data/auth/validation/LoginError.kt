package com.borysante.saveit.data.auth.validation

import com.borysante.saveit.data.error.Error

enum class LoginError(override val errorMessage: String) : Error {
    EMPTY_EMAIL("Email cannot be empty"),
    EMPTY_PASSWORD("Password cannot be empty"),
    LOGIN_FAILED("Login failed"),
    INVALID_EMAIL_FORMAT("Invalid email format"),
    UNKNOWN_ERROR("An unknown error occurred")
}