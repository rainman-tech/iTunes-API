package com.rain.itunes_api.repository

import com.rain.itunes_api.domain.model.ItunesMedia

//Repository used for api request
interface ItunesMediaRepository {

    suspend fun search(term: String, country: String, media: String): List<ItunesMedia>

}