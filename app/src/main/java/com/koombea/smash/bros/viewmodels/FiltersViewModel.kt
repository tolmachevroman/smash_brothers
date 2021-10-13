package com.koombea.smash.bros.viewmodels

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FiltersViewModel @Inject constructor(private val sharedPreferences: SharedPreferences) :
    ViewModel() {

        fun getFilterSettings() {
//            val s = sharedPreferences.getString()
        }

        fun saveFilterSettings() {
//            sharedPreferences.edit().
        }
}