package com.patriciafiona.marioworld.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*


@Parcelize
data class News(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val headline: String,
    val link: String,
    val date: String,
    val image: Int
): Parcelable
