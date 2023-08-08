package com.patriciafiona.financeapp.data.entities

import java.util.Date
import java.util.UUID

data class Transaction(
    val id: UUID = UUID.randomUUID(),
    val user: User,
    val type: TransactionType,
    val amount: Double,
    val date: String
)

enum class TransactionType {
    RECEIVED, OUTGOING
}
