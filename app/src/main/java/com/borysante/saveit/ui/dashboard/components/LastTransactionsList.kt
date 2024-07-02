package com.borysante.saveit.ui.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.borysante.saveit.data.dto.dashboard.Transaction
import com.borysante.saveit.data.dto.dashboard.TransactionCategory
import com.borysante.saveit.ui.components.ThemedText
import androidx.compose.foundation.isSystemInDarkTheme

@Composable
fun LastTransactionsList(transactions: List<Transaction>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LastTransactionsListTopText()
        LastTransactionsLazyList(transactions)
    }
}

@Composable
fun LastTransactionsListTopText() {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        ThemedText(
            text = "Last Transactions",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 8.dp)
        )
        TextButton(onClick = { /* Handle view all click */ }) {
            ThemedText(text = "View All")
        }
    }
}

@Composable
fun LastTransactionsLazyList(transactions: List<Transaction>) {
    LazyColumn {
        items(transactions.size, key = { it }) { index ->
            TransactionItem(transactions[index])
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction) {
    val isDarkTheme = isSystemInDarkTheme()
    val backgroundColor = if (isDarkTheme) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.background

    with(transaction) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { }
                .background(backgroundColor)
        ) {
            TransactionCategoryIcon(category = category)
            Spacer(modifier = Modifier.width(16.dp))
            TransactionText(title, date)
            Spacer(modifier = Modifier.weight(1f))
            TransactionAmount(amount)
        }
    }
}

@Composable
fun TransactionCategoryIcon(category: TransactionCategory) {
    Card(
        modifier = Modifier
            .size(70.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(id = category.icon),
                contentDescription = "Transaction Category Icon",
                tint = Color.Unspecified,
                modifier = Modifier.size(55.dp)
            )
        }
    }
}

@Composable
fun RowScope.TransactionText(title: String, date: String) {
    Column(
        verticalArrangement = Arrangement.Center, modifier = Modifier
            .fillMaxHeight()
            .weight(1f)
    ) {
        ThemedText(text = title)
        ThemedText(text = date)
    }
}

@Composable
fun TransactionAmount(amount: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End,
        modifier = Modifier
            .fillMaxHeight()
            .padding(end = 8.dp)
    ) {
        ThemedText(text = amount)
    }
}
