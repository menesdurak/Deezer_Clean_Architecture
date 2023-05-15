package com.menesdurak.appcentcasestudycleanarchitecture.domain.repository

import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.AlbumResponse
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.ArtistResponse
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.GenreResponse
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.TrackResponse

interface RemoteRepository {

    suspend fun getAllGenres(): GenreResponse

    suspend fun getAllArtists(genreId: Int): ArtistResponse

    suspend fun getAllAlbums(artistId: Int): AlbumResponse

    suspend fun getAllTracks(albumId: Int): TrackResponse
}