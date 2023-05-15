package com.menesdurak.appcentcasestudycleanarchitecture.domain.use_case.albums

import com.menesdurak.appcentcasestudycleanarchitecture.common.Resource
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.AlbumResponse
import com.menesdurak.appcentcasestudycleanarchitecture.domain.repository.RemoteRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllAlbumsUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
) {

    suspend operator fun invoke(artistId: Int): Resource<AlbumResponse> {
        return try {
            Resource.Success(remoteRepository.getAllAlbums(artistId))
        } catch (e: HttpException) {
            Resource.Error(e)
        } catch (e: IOException) {
            Resource.Error(e)
        }
    }
}