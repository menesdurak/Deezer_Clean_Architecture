package com.menesdurak.appcentcasestudycleanarchitecture.data.mapper

import com.menesdurak.appcentcasestudycleanarchitecture.common.mapper.ListMapper
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.TrackData
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.TrackUiData

class TrackDataMapper(private val albumImage: String) : ListMapper<TrackData,TrackUiData> {

    override fun map(input: List<TrackData>): List<TrackUiData> {
        return input.map {
            TrackUiData(
                duration = it.duration,
                id = it.id,
                preview = it.preview,
                title = it.title,
                image = albumImage
            )
        }
    }

}