package com.menesdurak.appcentcasestudycleanarchitecture.data.remote

import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.AlbumResponse
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.ArtistResponse
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.GenreResponse
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.TrackResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DeezerApi {

    @GET("genre")
    suspend fun getAllGenres(): GenreResponse

    @GET("genre/{genre_id}/artists")
    suspend fun getAllArtists(@Path("genre_id") genreId: Int): ArtistResponse

    @GET("artist/{artist_id}/albums")
    suspend fun getAllAlbums(@Path("artist_id") artistId: Int): AlbumResponse

    @GET("album/{album_id}/tracks")
    suspend fun getAllTracks(@Path("album_id") albumId: Int): TrackResponse
}