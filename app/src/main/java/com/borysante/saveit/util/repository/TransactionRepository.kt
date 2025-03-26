package com.borysante.saveit.util.repository

import com.borysante.saveit.data.dto.transactions.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun getTransactions(): Flow<List<Transaction>>?
    suspend fun addTransaction(transaction: Transaction)
    suspend fun deleteTransaction(transactionId: String)
    suspend fun modifyTransaction(transaction: Transaction)
}