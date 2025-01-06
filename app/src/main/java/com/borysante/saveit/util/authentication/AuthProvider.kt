package com.borysante.saveit.util.authentication

import com.borysante.saveit.data.auth.User
import com.borysante.saveit.data.auth.validation.LoginError
import com.borysante.saveit.data.result.ApiResult

typealias AuthenticationResult<T> = ApiResult<T, LoginError>

interface AuthProvider {
    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): AuthenticationResult<User>

    suspend fun signOut(): AuthenticationResult<Unit>
    suspend fun signInWithGoogle(idToken: String): AuthenticationResult<User>
    suspend fun sendPasswordResetEmail(email: String): AuthenticationResult<Unit>

    suspend fun getCurrentUser(): User?
    fun isLoggedIn(): Boolean
    suspend fun refreshToken(): String?

    object TokenExpired : Exception() {
        private fun readResolve(): Any = TokenExpired
        override val message: String = "Token expired. User needs to log in again"
    }
}