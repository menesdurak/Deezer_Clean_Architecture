package com.menesdurak.appcentcasestudycleanarchitecture.data.repository

import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.DeezerApi
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.AlbumResponse
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.ArtistResponse
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.GenreResponse
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.TrackResponse
import com.menesdurak.appcentcasestudycleanarchitecture.domain.repository.RemoteRepository
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val api: DeezerApi) : RemoteRepository {

    override suspend fun getAllGenres(): GenreResponse {
        return api.getAllGenres()
    }

    override suspend fun getAllArtists(genreId: Int): ArtistResponse {
        return api.getAllArtists(genreId)
    }

    override suspend fun getAllAlbums(artistId: Int): AlbumResponse {
        return api.getAllAlbums(artistId)
    }

    override suspend fun getAllTracks(albumId: Int): TrackResponse {
        return api.getAllTracks(albumId)
    }
}