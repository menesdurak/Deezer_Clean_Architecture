package com.menesdurak.appcentcasestudycleanarchitecture.domain.repository

import com.menesdurak.appcentcasestudycleanarchitecture.data.local.entity.FavoriteTrack

interface LocalRepository {

    suspend fun getAllFavoriteTracks(): List<FavoriteTrack>

    suspend fun addFavoriteTrack(favoriteTrack: FavoriteTrack)

    suspend fun deleteFavoriteTrackWithId(favoriteTrackId: Long)
}