package com.menesdurak.appcentcasestudycleanarchitecture.data.repository

import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.DeezerApi
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.ArtistResponse
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.GenreResponse
import com.menesdurak.appcentcasestudycleanarchitecture.domain.repository.RemoteRepository
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val api: DeezerApi) : RemoteRepository {

    override suspend fun getAllGenres(): GenreResponse {
        return api.getAllGenres()
    }

    override suspend fun getAllArtists(genreId: Int): ArtistResponse {
        return api.getAllArtists(genreId)
    }
}