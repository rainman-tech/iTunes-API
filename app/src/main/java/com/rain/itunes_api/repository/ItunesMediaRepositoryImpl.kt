package com.rain.itunes_api.repository

import com.rain.itunes_api.domain.model.ItunesMedia
import com.rain.itunes_api.network.ApiService
import com.rain.itunes_api.network.model.ItunesMediaDtoMapper

//Repository Implementation and mapping of data object from DTO to Domain Model
class ItunesMediaRepositoryImpl(
    private val apiService: ApiService,
    private val mapper: ItunesMediaDtoMapper
) : ItunesMediaRepository {

    override suspend fun search(term: String, country: String, media: String): List<ItunesMedia> {
        val result = apiService.search(term, country, media).results
        return mapper.toDomainList(result)
    }

}