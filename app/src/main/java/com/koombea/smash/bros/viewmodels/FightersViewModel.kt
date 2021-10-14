package com.koombea.smash.bros.viewmodels

import android.content.SharedPreferences
import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.koombea.smash.bros.R
import com.koombea.smash.bros.data.SmashBrosRepository
import com.koombea.smash.bros.data.models.Fighter
import com.koombea.smash.bros.data.models.Universe
import com.koombea.smash.bros.utils.Resource
import com.koombea.smash.bros.views.adapters.UniversesAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.await
import javax.inject.Inject

@HiltViewModel
class FightersViewModel @Inject constructor(
    private val repository: SmashBrosRepository,
    private val resources: Resources,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val fighters = mutableListOf<Fighter>()

    fun getUniverses(): LiveData<Resource<List<Universe>>> = liveData {
        emit(Resource.loading(listOf()))

        var errorMessage: String? = null
        val response = try {
            repository.getUniverses()
        } catch (e: Exception) {
            errorMessage = e.toString()
            null
        }

        if (response != null) {
            val universes = mutableListOf<Universe>()
            universes.add(0, Universe("", UniversesAdapter.ALL_UNIVERSES, ""))
            universes.addAll(response)
            emit(Resource.success(universes))
        } else {
            emit(Resource.error(null, errorMessage))
        }
    }

    fun getFighters(universeName: String = ""): LiveData<Resource<List<Fighter>>> = liveData {
        emit(Resource.loading(listOf()))

        //Simple cache
        if (fighters.isEmpty()) {
            var errorMessage: String? = null
            val response = try {
                repository.getFighters("", 0) //TODO refactor
            } catch (e: Exception) {
                errorMessage = e.toString()
                null
            }

            if (response != null) {
                fighters.addAll(response)
                emit(Resource.success(fighters.filterByRate().sort()))
            } else {
                emit(Resource.error(null, errorMessage))
            }
        } else {
            if (universeName == UniversesAdapter.ALL_UNIVERSES) {
                emit(Resource.success(fighters.filterByRate().sort()))
            } else {
                emit(
                    Resource.success(
                        fighters.filterByRate().filterByUniverseName(universeName)
                            .sort()
                    )
                )
            }

        }
    }

    private fun List<Fighter>.sort(): List<Fighter> {
        return when (sharedPreferences.getInt(
            resources.getString(R.string.key_sort_by),
            FiltersViewModel.DEFAULT_SORT_BY
        )) {
            1 -> this.sortedBy { it.name }
            2 -> this.sortedBy { it.price }
            3 -> this.sortedBy { it.rate }
            else -> this.sortedBy { it.downloads }
        }
    }

    private fun List<Fighter>.filterByRate(): List<Fighter> {
        val minStars = sharedPreferences.getInt(
            resources.getString(R.string.key_filter_by),
            FiltersViewModel.DEFAULT_STARS
        )
        return filter { it.rate >= minStars }
    }

    private fun List<Fighter>.filterByUniverseName(universeName: String): List<Fighter> {
        return filter { it.universe == universeName }
    }
}