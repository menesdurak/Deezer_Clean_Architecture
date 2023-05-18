package com.menesdurak.appcentcasestudycleanarchitecture.data.mapper

import com.menesdurak.appcentcasestudycleanarchitecture.common.mapper.ListMapper
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.GenreData
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.GenreUiData

class GenreDataMapper: ListMapper<GenreData, GenreUiData> {
    override fun map(input: List<GenreData>): List<GenreUiData> {
        return input.map {
            GenreUiData(
                id = it.id,
                name = it.name,
                picture = it.picture
            )
        }
    }
}