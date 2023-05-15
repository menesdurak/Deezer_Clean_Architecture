package com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto

data class TrackResponse(
    val data: List<TrackData>,
    val total: Int
)