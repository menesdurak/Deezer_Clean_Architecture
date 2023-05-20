package com.menesdurak.appcentcasestudycleanarchitecture.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deezer_table")
data class FavoriteTrack(
    @PrimaryKey
    val id: Long,
    val name: String,
    val preview: String,
    val length: Int,
    val image: String
)
