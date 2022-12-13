package com.patriciafiona.plantyshop.data.entity_and_enum

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Plant(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val description: String,
    val family: String,
    val genus: String,
    val price: Double,
    val plant_light: Light,
    val category: String,
    val height: Double,
    val size: Double,
    val image: Int,
    val rating: Double
): Parcelable
