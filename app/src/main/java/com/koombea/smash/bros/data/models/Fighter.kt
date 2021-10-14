package com.koombea.smash.bros.data.models

import java.io.Serializable

data class Fighter(
    val objectID: String,
    val name: String,
    val universe: String,
    val price: String,
    val popular: Boolean,
    val rate: Int,
    val downloads: String,
    val description: String
) : Serializable