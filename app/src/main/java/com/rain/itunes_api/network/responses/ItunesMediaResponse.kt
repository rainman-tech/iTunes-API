package com.rain.itunes_api.network.responses

import com.rain.itunes_api.network.model.ItunesMediaDto

data class ItunesMediaResponse (
    val resultCount: Int,
    val results: List<ItunesMediaDto>
)