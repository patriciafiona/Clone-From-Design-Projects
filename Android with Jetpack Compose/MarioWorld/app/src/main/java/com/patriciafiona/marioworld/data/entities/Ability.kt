package com.patriciafiona.marioworld.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ability(
    val acceleration: Double,
    val maxSpeed: Double,
    val technique: Double,
    val power: Double,
    val stamina: Double,
): Parcelable
