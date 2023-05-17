package com.menesdurak.appcentcasestudycleanarchitecture.data.repository

import com.menesdurak.appcentcasestudycleanarchitecture.data.local.DeezerDao
import com.menesdurak.appcentcasestudycleanarchitecture.data.local.entity.FavoriteTrack
import com.menesdurak.appcentcasestudycleanarchitecture.domain.repository.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val dao: DeezerDao) : LocalRepository {

    override suspend fun getAllFavoriteTracks(): List<FavoriteTrack> {
        return dao.getAllFavoriteTracks()
    }

    override suspend fun addFavoriteTrack(favoriteTrack: FavoriteTrack) {
        dao.addFavoriteTrack(favoriteTrack)
    }

    override suspend fun deleteFavoriteTrackWithId(favoriteTrackId: Long) {
        dao.deleteFavoriteTrackWithId(favoriteTrackId)
    }
}