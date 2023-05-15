package com.menesdurak.appcentcasestudycleanarchitecture.data.remote

import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.ArtistResponse
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.GenreResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DeezerApi {

    @GET("genre")
    suspend fun getAllGenres(): GenreResponse

    @GET("genre/{genre_id}/artists")
    suspend fun getAllArtists(@Path("genre_id") genreId: Int): ArtistResponse
}