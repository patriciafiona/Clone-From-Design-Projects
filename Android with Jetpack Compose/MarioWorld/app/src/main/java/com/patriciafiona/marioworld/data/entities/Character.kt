package com.patriciafiona.marioworld.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Character(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val fullName: String,
    val dialog: String,
    val species: String,
    val description: String,
    val imageOpen: Int,
    val imageClose: Int,
    val imageFull: Int? = null,
    val backgroundColor: ArrayList<Int>, //R, G, B
    val characterSound: ArrayList<Int>,
    val ability: Ability
): Parcelable
