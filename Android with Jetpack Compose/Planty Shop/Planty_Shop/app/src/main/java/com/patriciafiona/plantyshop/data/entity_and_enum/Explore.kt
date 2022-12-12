package com.patriciafiona.plantyshop.data.entity_and_enum

import java.util.UUID

data class Explore(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val description: String,
    val image: Int,
    val url: String
)