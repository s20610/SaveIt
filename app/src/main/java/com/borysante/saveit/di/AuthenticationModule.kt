package com.borysante.saveit.di

import com.borysante.saveit.util.authentication.AuthProvider
import com.borysante.saveit.util.authentication.firebase.FirebaseAuthProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
fun interface AuthenticationModule {
    @Binds
    @Singleton
    fun bindAuthProvider(
        firebaseAuthProvider: FirebaseAuthProvider
    ): AuthProvider
}