package com.menesdurak.appcentcasestudycleanarchitecture.domain.repository

import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.ArtistResponse
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.GenreResponse

interface RemoteRepository {

    suspend fun getAllGenres(): GenreResponse

    suspend fun getAllArtists(genreId: Int): ArtistResponse
}