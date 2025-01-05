package com.borysante.saveit.di

import com.borysante.saveit.util.repository.FirestoreTransactionRepository
import com.borysante.saveit.util.repository.TransactionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
fun interface TransactionsModule {

    @Binds
    @Singleton
    fun bindTransactionRepository(
        firestoreTransactionRepository: FirestoreTransactionRepository
    ): TransactionRepository
}