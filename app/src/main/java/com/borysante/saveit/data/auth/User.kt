package com.borysante.saveit.data.auth

data class User(
    val displayName: String?,
    val email: String?,
    val uid: String,
    val token: String? = null
)