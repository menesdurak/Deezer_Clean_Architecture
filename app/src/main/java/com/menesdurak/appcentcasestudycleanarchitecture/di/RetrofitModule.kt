package com.menesdurak.appcentcasestudycleanarchitecture.di

import com.menesdurak.appcentcasestudycleanarchitecture.common.Constants.BASE_URL
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.DeezerApi
import com.menesdurak.appcentcasestudycleanarchitecture.data.repository.RemoteRepositoryImpl
import com.menesdurak.appcentcasestudycleanarchitecture.domain.repository.RemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideDeezerApi(): DeezerApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DeezerApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteRepository(api: DeezerApi): RemoteRepository {
        return RemoteRepositoryImpl(api)
    }
}