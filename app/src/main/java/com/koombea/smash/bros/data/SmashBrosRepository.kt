package com.koombea.smash.bros.data

import com.koombea.smash.bros.data.webservice.WebService
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient

@Module
@InstallIn(SingletonComponent::class)
class SmashBrosRepository @Inject constructor() {

    private var apiClient: WebService

    init {

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

        val retrofitClient = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(WebService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiClient = retrofitClient.create(WebService::class.java)

        //TODO You error handle by catching the HttpException which is thrown by the retrofit framework for non HTTP 200-ish response codes
    }

    suspend fun getUniverses() = apiClient.getUniverses()

    suspend fun getFighters(universe: String, rate: Int) = apiClient.getFighters(universe, rate)

}