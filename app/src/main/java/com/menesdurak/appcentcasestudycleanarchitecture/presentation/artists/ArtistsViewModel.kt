package com.menesdurak.appcentcasestudycleanarchitecture.presentation.artists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.menesdurak.appcentcasestudycleanarchitecture.common.Resource
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.ArtistResponse
import com.menesdurak.appcentcasestudycleanarchitecture.domain.use_case.artists.GetAllArtistsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistsViewModel @Inject constructor(
    private val getAllArtistsUseCase: GetAllArtistsUseCase
): ViewModel() {

    private val _artistsList = MutableLiveData<Resource<ArtistResponse>>(Resource.Loading)
    val artistsList: LiveData<Resource<ArtistResponse>> = _artistsList

    fun getArtists(genreId: Int) {
        viewModelScope.launch {
            _artistsList.value = Resource.Loading
            _artistsList.value = getAllArtistsUseCase(genreId)!!
        }
    }
}