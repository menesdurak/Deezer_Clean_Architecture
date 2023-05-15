package com.menesdurak.appcentcasestudycleanarchitecture.presentation.genres

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.menesdurak.appcentcasestudycleanarchitecture.common.Resource
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.GenreResponse
import com.menesdurak.appcentcasestudycleanarchitecture.domain.use_case.genres.GetAllGenresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(
    private val getAllGenresUseCase: GetAllGenresUseCase
) : ViewModel() {

    private val _genresList = MutableLiveData<Resource<GenreResponse>>(Resource.Loading)
    val genresList: LiveData<Resource<GenreResponse>> = _genresList

    fun getGenres() {
        viewModelScope.launch {
            _genresList.value = Resource.Loading
            _genresList.value = getAllGenresUseCase()!!
        }
    }
}