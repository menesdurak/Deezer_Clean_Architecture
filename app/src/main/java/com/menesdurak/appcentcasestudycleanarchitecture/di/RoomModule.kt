package com.menesdurak.appcentcasestudycleanarchitecture.di

import android.app.Application
import com.menesdurak.appcentcasestudycleanarchitecture.data.local.DeezerDao
import com.menesdurak.appcentcasestudycleanarchitecture.data.local.DeezerDatabase
import com.menesdurak.appcentcasestudycleanarchitecture.data.repository.LocalRepositoryImpl
import com.menesdurak.appcentcasestudycleanarchitecture.domain.repository.LocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun getDeezerDatabase(context: Application): DeezerDatabase {
        return DeezerDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun getFavoriteTrackDao(favoriteTrackDatabase: DeezerDatabase): DeezerDao {
        return favoriteTrackDatabase.getDeezerDao()
    }

    @Provides
    @Singleton
    fun provideLocalRepository(dao: DeezerDao): LocalRepository {
        return LocalRepositoryImpl(dao)
    }
}