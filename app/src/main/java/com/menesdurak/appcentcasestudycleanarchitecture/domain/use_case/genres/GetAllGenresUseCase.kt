package com.menesdurak.appcentcasestudycleanarchitecture.domain.use_case.genres

import com.menesdurak.appcentcasestudycleanarchitecture.common.Resource
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.GenreResponse
import com.menesdurak.appcentcasestudycleanarchitecture.domain.repository.RemoteRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllGenresUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
) {
    suspend operator fun invoke(): Resource<GenreResponse> {
        return try {
            Resource.Success(remoteRepository.getAllGenres())
        } catch (e: HttpException) {
            Resource.Error(e)
        } catch (e: IOException) {
            Resource.Error(e)
        }
    }
}