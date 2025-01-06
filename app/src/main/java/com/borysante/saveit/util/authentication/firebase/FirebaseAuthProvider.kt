package com.borysante.saveit.util.authentication.firebase

import com.borysante.saveit.data.auth.User
import com.borysante.saveit.data.auth.validation.LoginError
import com.borysante.saveit.data.result.ApiResult
import com.borysante.saveit.util.authentication.AuthProvider
import com.borysante.saveit.util.authentication.AuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAuthProvider @Inject constructor(
    private val auth: FirebaseAuth,
) : AuthProvider {
    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): AuthenticationResult<User> = try {
        val authResult = auth.signInWithEmailAndPassword(email, password).await()
        val user = authResult.user
        if (user != null) {
            ApiResult.Success(user.toUser())
        } else {
            ApiResult.Error(LoginError.LOGIN_FAILED)
        }
    } catch (e: Exception) {
        ApiResult.Error(LoginError.UNKNOWN_ERROR)
    }

    private suspend fun FirebaseUser.toUser(): User {
        return User(
            displayName = this.displayName,
            email = this.email,
            uid = this.uid,
            token = this.getIdToken(false).await().token
        )
    }

    override suspend fun signOut(): AuthenticationResult<Unit> = try {
        auth.signOut()
        ApiResult.Success(Unit)
    } catch (e: Exception) {
        ApiResult.Error(LoginError.UNKNOWN_ERROR)
    }

    override suspend fun signInWithGoogle(idToken: String): AuthenticationResult<User> {
        TODO("Not yet implemented")
    }

    override suspend fun sendPasswordResetEmail(email: String): AuthenticationResult<Unit> {
        try {
            auth.sendPasswordResetEmail(email)
            return ApiResult.Success(Unit)
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            return ApiResult.Error(LoginError.INVALID_EMAIL_FORMAT)
        } catch (e: Exception) {
            return ApiResult.Error(LoginError.UNKNOWN_ERROR)
        }
    }

    override suspend fun getCurrentUser() = auth.currentUser?.toUser()

    override fun isLoggedIn() = auth.currentUser != null

    override suspend fun refreshToken(): String? {
        val user = auth.currentUser ?: return null
        return try {
            user.getIdToken(true).await().token
        } catch (e: FirebaseAuthInvalidUserException) {
            throw AuthProvider.TokenExpired
        }
    }
}