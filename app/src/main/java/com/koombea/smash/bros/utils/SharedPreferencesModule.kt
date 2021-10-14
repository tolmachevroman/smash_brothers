package com.koombea.smash.bros.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferencesModule {

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("smash_bros_preferences", Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideResources(@ApplicationContext context: Context): Resources {
        return context.resources
    }
}