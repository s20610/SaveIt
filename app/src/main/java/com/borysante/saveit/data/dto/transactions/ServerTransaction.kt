package com.borysante.saveit.data.dto.transactions

import com.borysante.saveit.util.formatter.DateFormatter.toTimestamp
import com.google.firebase.Timestamp

data class ServerTransaction(
    val id: String? = null,
    val title: String = "",
    val amount: Float = 0.0f,
    val category: TransactionCategory = TransactionCategory.FOOD,
    val serverTimestamp: Timestamp = Timestamp.now(),
) {
    companion object {
        fun fromTransaction(transaction: Transaction) = ServerTransaction(
            id = transaction.id,
            title = transaction.title,
            amount = transaction.amount,
            category = transaction.category,
            serverTimestamp = transaction.date.toTimestamp()
        )
    }
}