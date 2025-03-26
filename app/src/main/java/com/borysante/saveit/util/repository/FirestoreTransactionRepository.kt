package com.borysante.saveit.util.repository

import com.borysante.saveit.data.dto.transactions.Transaction
import com.borysante.saveit.util.authentication.AuthProvider
import com.borysante.saveit.util.authentication.firebase.FirebaseAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirestoreTransactionRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val authProvider: AuthProvider
) : TransactionRepository {

    private suspend fun getTransactionsCollection() =
        authProvider.getCurrentUser()?.uid?.let { firestore.collection("users").document(it).collection("transactions") }

    override suspend fun getTransactions(): Flow<List<Transaction>>? {
        return getTransactionsCollection()?.snapshots()?.map { snapshot ->
            snapshot.documents.mapNotNull { document ->
                document.toObject(Transaction::class.java)
            }
        }
    }

    override suspend fun addTransaction(transaction: Transaction) {
        getTransactionsCollection()?.add(transaction)?.await()
    }

    override suspend fun deleteTransaction(transactionId: String) {
        getTransactionsCollection()?.document(transactionId)?.delete()?.await()
    }

    override suspend fun modifyTransaction(transaction: Transaction) {
        transaction.id?.let {
            getTransactionsCollection()?.document(it)?.set(transaction)?.await()
        }
    }
}

