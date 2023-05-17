package com.menesdurak.appcentcasestudycleanarchitecture.domain.use_case.favorites.delete_favorite

import com.menesdurak.appcentcasestudycleanarchitecture.common.Resource
import com.menesdurak.appcentcasestudycleanarchitecture.domain.repository.LocalRepository
import java.io.IOException
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(favoriteTrackId: Long){
        try {
            Resource.Success(localRepository.deleteFavoriteTrackWithId(favoriteTrackId))
        } catch (e: IOException) {
            Resource.Error(e)
        }
    }
}