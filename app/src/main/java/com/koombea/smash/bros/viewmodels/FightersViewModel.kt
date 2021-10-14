package com.koombea.smash.bros.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.koombea.smash.bros.data.SmashBrosRepository
import com.koombea.smash.bros.data.models.Fighter
import com.koombea.smash.bros.data.models.Universe
import com.koombea.smash.bros.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.await
import javax.inject.Inject

@HiltViewModel
class FightersViewModel @Inject constructor(private val repository: SmashBrosRepository) :
    ViewModel() {

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
            universes.add(0, Universe("", "All", ""))
            universes.addAll(response)
            emit(Resource.success(universes))
        } else {
            emit(Resource.error(null, errorMessage))
        }
    }

    fun getFighters(): LiveData<Resource<List<Fighter>>> = liveData {
        emit(Resource.loading(listOf()))

        var errorMessage: String? = null
        val response = try {
            repository.getFighters("", 0) //TODO change
        } catch (e: Exception) {
            errorMessage = e.toString()
            null
        }

        if (response != null) {
            emit(Resource.success(response))
        } else {
            emit(Resource.error(null, errorMessage))
        }
    }
}