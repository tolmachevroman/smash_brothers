package com.koombea.smash.bros.data.webservice

import com.koombea.smash.bros.data.models.Fighter
import com.koombea.smash.bros.data.models.Universe
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    companion object {
        const val BASE_URL = "https://593cdf8fb56f410011e7e7a9.mockapi.io/"
    }

    @GET("universes")
    suspend fun getUniverses(): List<Universe>

    @GET("fighters")
    suspend fun getFighters(@Query("universe") universe: String): List<Fighter>
}