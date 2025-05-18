package com.borysante.saveit.util.repository

import android.util.Log
import com.borysante.saveit.data.dto.transactions.ServerTransaction
import com.borysante.saveit.data.dto.transactions.Transaction
import com.borysante.saveit.util.authentication.AuthProvider
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirestoreTransactionRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val authProvider: AuthProvider
) : TransactionRepository {

    private suspend fun getTransactionsCollection() =
        authProvider.getCurrentUser()?.uid?.let {
            firestore.collection("users").document(it).collection("transactions")
        }

    private suspend fun orderedTransactions() =
        getTransactionsCollection()?.orderBy("serverTimestamp", Query.Direction.DESCENDING)

    override suspend fun getTransactions(): Flow<List<Transaction>>? {
        return orderedTransactions()?.snapshots()?.map { snapshot ->
            snapshot.documents.mapNotNull { document ->
                document.toTransaction()
            }
        }
    }

    override suspend fun getLastXTransactions(limit: Long): Flow<List<Transaction>>? {
        return orderedTransactions()?.limit(limit)?.snapshots()?.map { snapshot ->
            snapshot.documents.mapNotNull { document ->
                document.toTransaction()
            }
        }
    }

    override suspend fun getThisMonthTransactions(): Flow<List<Transaction>>? {
        return orderedTransactions()?.whereGreaterThanOrEqualTo("serverTimestamp", thisMonthStart())
            ?.whereLessThanOrEqualTo("serverTimestamp", thisMonthEnd())?.snapshots()
            ?.map { snapshot ->
                snapshot.documents.mapNotNull { document ->
                    document.toTransaction()
                }
            }
    }

    fun thisMonthStart(): Timestamp {
        val now = LocalDate.now()
        val startOfMonth = LocalDate.of(now.year, now.month, 1)
        return Timestamp(startOfMonth.toEpochDay(), 0)
    }

    fun thisMonthEnd(): Timestamp {
        val now = LocalDate.now()
        val endOfMonth = LocalDate.of(now.year, now.month, now.month.length(now.isLeapYear))
        return Timestamp(endOfMonth.toEpochDay(), 0)
    }

    private fun DocumentSnapshot.toTransaction(): Transaction? {
        return try {
            toObject(ServerTransaction::class.java)?.let {
                Transaction.fromServerTransaction(it)
            }
        } catch (e: Exception) {
            Log.d("FirestoreTransactionRepository", "Error converting document to Transaction: $e")
            null
        }
    }

    override suspend fun addTransaction(transaction: Transaction) {
        getTransactionsCollection()?.add(ServerTransaction.fromTransaction(transaction))?.await()
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

