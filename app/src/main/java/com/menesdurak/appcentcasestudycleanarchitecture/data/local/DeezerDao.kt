package com.menesdurak.appcentcasestudycleanarchitecture.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.menesdurak.appcentcasestudycleanarchitecture.data.local.entity.FavoriteTrack

@Dao
interface DeezerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavoriteTrack(favoriteTrack: FavoriteTrack)

    @Query("DELETE FROM deezer_table WHERE id = :favoriteTrackId")
    suspend fun deleteFavoriteTrackWithId(favoriteTrackId: Long)

    @Query("SELECT * FROM deezer_table ORDER BY id ASC")
    suspend fun getAllFavoriteTracks(): List<FavoriteTrack>
}