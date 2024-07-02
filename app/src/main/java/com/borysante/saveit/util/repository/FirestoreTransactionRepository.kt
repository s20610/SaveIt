package com.borysante.saveit.util.repository

import com.borysante.saveit.data.dto.transactions.Transaction
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirestoreTransactionRepository @Inject constructor(
    firestore: FirebaseFirestore
) : TransactionRepository {

    private val transactionsCollection = firestore.collection("transactions")

    override fun getTransactions(): Flow<List<Transaction>> {
        return transactionsCollection.snapshots().map { snapshot ->
            snapshot.documents.mapNotNull { document ->
                document.toObject(Transaction::class.java)
            }
        }
    }

    override suspend fun addTransaction(transaction: Transaction) {
        transactionsCollection.add(transaction).await()
    }

    override suspend fun deleteTransaction(transactionId: String) {
        transactionsCollection.document(transactionId).delete().await()
    }
}

