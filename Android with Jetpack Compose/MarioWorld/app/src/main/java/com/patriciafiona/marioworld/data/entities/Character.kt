package com.patriciafiona.marioworld.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Character(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val description: String,
    val imageOpen: Int,
    val imageClose: Int,
    val backgroundColor: ArrayList<Int> //R, G, B
): Parcelable
