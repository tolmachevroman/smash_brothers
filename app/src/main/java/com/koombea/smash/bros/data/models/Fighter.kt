package com.koombea.smash.bros.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fighter(
    val objectID: String,
    val name: String,
    val universe: String,
    val price: Int,
    val popular: Boolean,
    val rate: Int,
    val downloads: Int,
    val description: String,
    val imageURL: String
) : Parcelable

fun List<Fighter>.filterByRate(rate: Int): List<Fighter> = filter { it.rate >= rate }

fun List<Fighter>.sortByField(sortBy: Int): List<Fighter> {
    return when (sortBy) {
        1 -> this.sortedBy { it.name }
        2 -> this.sortedBy { it.price }
        3 -> this.sortedBy { it.rate }
        else -> this.sortedBy { it.downloads }
    }
}