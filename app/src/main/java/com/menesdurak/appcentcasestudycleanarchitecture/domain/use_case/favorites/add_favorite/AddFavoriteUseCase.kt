package com.menesdurak.appcentcasestudycleanarchitecture.domain.use_case.favorites.add_favorite

import com.menesdurak.appcentcasestudycleanarchitecture.common.Resource
import com.menesdurak.appcentcasestudycleanarchitecture.data.local.entity.FavoriteTrack
import com.menesdurak.appcentcasestudycleanarchitecture.domain.repository.LocalRepository
import java.io.IOException
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(favoriteTrack: FavoriteTrack){
        try {
            Resource.Success(localRepository.addFavoriteTrack(favoriteTrack))
        } catch (e: IOException) {
            Resource.Error(e)
        }
    }
}