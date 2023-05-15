package com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto

data class AlbumResponse(
    val data: List<AlbumData>,
    val next: String,
    val total: Int
)