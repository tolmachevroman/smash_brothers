package com.koombea.smash.bros.data.models

import java.io.Serializable

data class Universe(
    val objectID: String,
    val name: String,
    val description: String
) : Serializable