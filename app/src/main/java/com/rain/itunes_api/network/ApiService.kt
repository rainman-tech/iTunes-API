package com.rain.itunes_api.network

import com.rain.itunes_api.network.responses.ItunesMediaResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    suspend fun search(
        @Query("term") term: String,
        @Query("country") country: String,
        @Query("media") media: String
    ) : ItunesMediaResponse

}