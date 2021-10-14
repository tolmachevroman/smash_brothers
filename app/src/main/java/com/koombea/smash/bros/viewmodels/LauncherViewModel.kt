package com.koombea.smash.bros.viewmodels

import android.content.SharedPreferences
import android.content.res.Resources
import androidx.lifecycle.ViewModel
import com.koombea.smash.bros.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LauncherViewModel @Inject constructor(
    private val resources: Resources,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    fun hasBeenOpenedBefore(): Boolean {
        return sharedPreferences.getBoolean(
            resources.getString(R.string.key_opened_before), false
        )
    }

    fun setHasBeenOpened() {
        sharedPreferences.edit()
            .putBoolean(resources.getString(R.string.key_opened_before), true).apply()
    }
}