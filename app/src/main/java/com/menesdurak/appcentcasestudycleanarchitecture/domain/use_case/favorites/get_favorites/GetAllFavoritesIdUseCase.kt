package com.menesdurak.appcentcasestudycleanarchitecture.domain.use_case.favorites.get_favorites

import com.menesdurak.appcentcasestudycleanarchitecture.common.Resource
import com.menesdurak.appcentcasestudycleanarchitecture.domain.repository.LocalRepository
import java.io.IOException
import javax.inject.Inject

class GetAllFavoritesIdUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(): Resource<List<Long>> {
        return try {
            Resource.Success(localRepository.getAllFavoriteTracks().map { favoriteTrack ->
                favoriteTrack.id
            })
        } catch (e: IOException) {
            Resource.Error(e)
        }
    }
}