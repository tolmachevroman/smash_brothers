package com.koombea.smash.bros.data.webservice

import com.koombea.smash.bros.data.models.Universe
import retrofit2.Response
import retrofit2.http.GET

interface WebService {

    companion object {
        val BASE_URL = "https://593cdf8fb56f410011e7e7a9.mockapi.io/"
    }

    @GET("universes")
    suspend fun getUniverses(): Response<List<Universe>?>
}