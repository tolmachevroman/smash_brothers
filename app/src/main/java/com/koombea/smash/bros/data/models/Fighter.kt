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
    val downloads: String,
    val description: String,
    val imageURL: String
) : Parcelable