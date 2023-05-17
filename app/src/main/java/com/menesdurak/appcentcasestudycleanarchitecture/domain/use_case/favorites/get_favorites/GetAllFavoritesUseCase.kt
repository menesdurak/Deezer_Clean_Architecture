package com.menesdurak.appcentcasestudycleanarchitecture.domain.use_case.favorites.get_favorites

import com.menesdurak.appcentcasestudycleanarchitecture.common.Resource
import com.menesdurak.appcentcasestudycleanarchitecture.data.local.entity.FavoriteTrack
import com.menesdurak.appcentcasestudycleanarchitecture.domain.repository.LocalRepository
import java.io.IOException
import javax.inject.Inject

class GetAllFavoritesUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(): Resource<List<FavoriteTrack>> {
        return try {
            Resource.Success(localRepository.getAllFavoriteTracks())
        } catch (e: IOException) {
            Resource.Error(e)
        }
    }
}