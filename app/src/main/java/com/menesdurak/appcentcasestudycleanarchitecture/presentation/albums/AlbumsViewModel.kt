package com.menesdurak.appcentcasestudycleanarchitecture.presentation.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.menesdurak.appcentcasestudycleanarchitecture.common.Resource
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.AlbumResponse
import com.menesdurak.appcentcasestudycleanarchitecture.domain.use_case.albums.GetAllAlbumsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private val getAllAlbumsUseCase: GetAllAlbumsUseCase
): ViewModel() {

    private val _albumsList = MutableLiveData<Resource<AlbumResponse>>(Resource.Loading)
    val albumsList: LiveData<Resource<AlbumResponse>> = _albumsList

    fun getAlbums(artistId: Int) {
        viewModelScope.launch {
            _albumsList.value = Resource.Loading
            _albumsList.value = getAllAlbumsUseCase(artistId)!!
        }
    }
}