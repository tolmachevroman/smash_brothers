package com.koombea.smash.bros.viewmodels

import android.content.SharedPreferences
import android.content.res.Resources
import androidx.lifecycle.ViewModel
import com.koombea.smash.bros.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FiltersViewModel @Inject constructor(
    private val resources: Resources,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    fun getSortBy(): Int {
        //Values from 1..4 correspond to Name..Downloads in UI
        return sharedPreferences.getInt(resources.getString(R.string.key_sort_by), 1)
    }

    fun setSortBy(newValue: Int) {
        sharedPreferences.edit().putInt(resources.getString(R.string.key_sort_by), newValue).apply()
    }

    fun getFilterBy(): Int {
        return sharedPreferences.getInt(resources.getString(R.string.key_filter_by), 0)
    }

    fun setFilterBy(newValue: Int) {
        sharedPreferences.edit().putInt(resources.getString(R.string.key_filter_by), newValue)
            .apply()
    }

    fun reset() {
        setSortBy(1)
        setFilterBy(0)
    }
}