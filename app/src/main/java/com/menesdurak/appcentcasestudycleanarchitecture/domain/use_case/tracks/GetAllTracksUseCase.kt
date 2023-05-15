package com.menesdurak.appcentcasestudycleanarchitecture.domain.use_case.tracks

import com.menesdurak.appcentcasestudycleanarchitecture.common.Resource
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.TrackResponse
import com.menesdurak.appcentcasestudycleanarchitecture.domain.repository.RemoteRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllTracksUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
) {
    suspend operator fun invoke(albumId: Int): Resource<TrackResponse> {
        return try {
            Resource.Success(remoteRepository.getAllTracks(albumId))
        } catch (e: HttpException) {
            Resource.Error(e)
        } catch (e: IOException) {
            Resource.Error(e)
        }
    }
}