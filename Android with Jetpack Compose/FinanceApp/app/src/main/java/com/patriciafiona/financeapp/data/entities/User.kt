package com.patriciafiona.financeapp.data.entities

import java.util.UUID

data class User(
    val id: UUID = UUID.randomUUID(),
    val firstName: String,
    val lastName: String,
    val profilePicture: Int
)
