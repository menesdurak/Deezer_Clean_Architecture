package com.menesdurak.appcentcasestudycleanarchitecture.presentation.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.menesdurak.appcentcasestudycleanarchitecture.common.Resource
import com.menesdurak.appcentcasestudycleanarchitecture.data.local.entity.FavoriteTrack
import com.menesdurak.appcentcasestudycleanarchitecture.domain.use_case.favorites.add_favorite.AddFavoriteUseCase
import com.menesdurak.appcentcasestudycleanarchitecture.domain.use_case.favorites.delete_favorite.DeleteFavoriteUseCase
import com.menesdurak.appcentcasestudycleanarchitecture.domain.use_case.favorites.get_favorites.GetAllFavoritesIdUseCase
import com.menesdurak.appcentcasestudycleanarchitecture.domain.use_case.favorites.get_favorites.GetAllFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase,
    private val getAllFavoritesIdUseCase: GetAllFavoritesIdUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : ViewModel() {

    private val _favoriteTracksList = MutableLiveData<Resource<List<FavoriteTrack>>>(Resource.Loading)
    val favoriteTracksList: LiveData<Resource<List<FavoriteTrack>>> = _favoriteTracksList

    private val _favoriteTracksIdList = MutableLiveData<Resource<List<Long>>>(Resource.Loading)
    val favoriteTracksIdList: LiveData<Resource<List<Long>>> = _favoriteTracksIdList

    fun getAllFavoriteTracks() {
        viewModelScope.launch {
            _favoriteTracksList.value = Resource.Loading
            _favoriteTracksList.value = getAllFavoritesUseCase()!!
        }
    }

    fun getAllFavoriteTracksId() {
        viewModelScope.launch {
            _favoriteTracksIdList.value = Resource.Loading
            _favoriteTracksIdList.value = getAllFavoritesIdUseCase()!!
        }
    }

    fun addTrackToFavorites(favoriteTrack: FavoriteTrack) {
        viewModelScope.launch {
            addFavoriteUseCase(favoriteTrack)
        }
    }

    fun deleteTrackFromFavorites(favoriteTrackId: Long) {
        viewModelScope.launch {
            deleteFavoriteUseCase(favoriteTrackId)
        }
    }
}