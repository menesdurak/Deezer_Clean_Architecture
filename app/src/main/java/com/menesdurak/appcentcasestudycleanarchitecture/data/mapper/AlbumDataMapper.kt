package com.menesdurak.appcentcasestudycleanarchitecture.data.mapper

import com.menesdurak.appcentcasestudycleanarchitecture.common.mapper.ListMapper
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.AlbumData
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.AlbumUiData

class AlbumDataMapper() : ListMapper<AlbumData, AlbumUiData> {
    override fun map(input: List<AlbumData>): List<AlbumUiData> {
        return input.map {
            AlbumUiData(
                id = it.id,
                title = it.title,
                picture = it.cover_medium
            )
        }
    }
}