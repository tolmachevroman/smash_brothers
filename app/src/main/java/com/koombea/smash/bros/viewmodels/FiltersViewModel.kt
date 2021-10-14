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

    companion object {
        //Values from 1..4 correspond to Name..Downloads in UI, default value is Name
        const val DEFAULT_SORT_BY = 1

        //Default value is zero stars
        const val DEFAULT_STARS = 0
    }


    fun getSortBy(): Int {
        return sharedPreferences.getInt(resources.getString(R.string.key_sort_by), DEFAULT_SORT_BY)
    }

    fun setSortBy(newValue: Int) {
        sharedPreferences.edit().putInt(resources.getString(R.string.key_sort_by), newValue).apply()
    }

    fun getFilterBy(): Int {
        return sharedPreferences.getInt(resources.getString(R.string.key_filter_by), DEFAULT_STARS)
    }

    fun setFilterBy(newValue: Int) {
        sharedPreferences.edit().putInt(resources.getString(R.string.key_filter_by), newValue)
            .apply()
    }

    fun reset() {
        setSortBy(DEFAULT_SORT_BY)
        setFilterBy(DEFAULT_STARS)
    }
}