package com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto

data class TrackUiData(
    val duration: Int,
    val id: Long,
    val preview: String,
    val title: String,
    val image: String,
    var isFavorite: Boolean = false,
)