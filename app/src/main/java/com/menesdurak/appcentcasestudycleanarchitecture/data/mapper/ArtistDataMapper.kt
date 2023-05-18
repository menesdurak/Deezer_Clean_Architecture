package com.menesdurak.appcentcasestudycleanarchitecture.data.mapper

import com.menesdurak.appcentcasestudycleanarchitecture.common.mapper.ListMapper
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.ArtistData
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.ArtistUiData

class ArtistDataMapper : ListMapper<ArtistData, ArtistUiData> {
    override fun map(input: List<ArtistData>): List<ArtistUiData> {
        return input.map {
            ArtistUiData(
                id = it.id,
                name = it.name,
                picture = it.picture_medium
            )
        }
    }
}