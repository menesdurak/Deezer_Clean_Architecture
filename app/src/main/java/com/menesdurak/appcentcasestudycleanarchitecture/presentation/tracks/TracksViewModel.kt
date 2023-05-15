package com.menesdurak.appcentcasestudycleanarchitecture.presentation.tracks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.menesdurak.appcentcasestudycleanarchitecture.common.Resource
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.TrackResponse
import com.menesdurak.appcentcasestudycleanarchitecture.domain.use_case.tracks.GetAllTracksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TracksViewModel @Inject constructor(
    private val getAllTracksUseCase: GetAllTracksUseCase
): ViewModel() {

    private val _tracksList = MutableLiveData<Resource<TrackResponse>>(Resource.Loading)
    val tracksList: LiveData<Resource<TrackResponse>> = _tracksList

    fun getTracks(albumId: Int) {
        viewModelScope.launch {
            _tracksList.value = Resource.Loading
            _tracksList.value = getAllTracksUseCase(albumId)!!
        }
    }
}