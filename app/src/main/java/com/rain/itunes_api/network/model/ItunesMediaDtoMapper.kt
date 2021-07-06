package com.rain.itunes_api.network.model

import com.rain.itunes_api.domain.model.ItunesMedia
import com.rain.itunes_api.domain.util.DomainMapper

class ItunesMediaDtoMapper : DomainMapper<ItunesMediaDto, ItunesMedia> {

    override fun mapToDomainModel(model: ItunesMediaDto): ItunesMedia {
        return ItunesMedia(
            artistName = model.artistName,
            artworkUrl100 = model.artworkUrl100,
            artworkUrl30 = model.artworkUrl30,
            artworkUrl60 = model.artworkUrl60,
            collectionArtistId = model.collectionArtistId,
            collectionArtistViewUrl = model.collectionArtistViewUrl,
            collectionCensoredName = model.collectionCensoredName,
            collectionExplicitness = model.collectionExplicitness,
            collectionHdPrice = model.collectionHdPrice,
            collectionId = model.collectionId,
            collectionName = model.collectionName,
            collectionPrice = model.collectionPrice,
            collectionViewUrl = model.collectionViewUrl,
            contentAdvisoryRating = model.contentAdvisoryRating,
            country = model.country,
            currency = model.currency,
            discCount = model.discCount,
            discNumber = model.discNumber,
            hasITunesExtras = model.hasITunesExtras,
            kind = model.kind,
            longDescription = model.longDescription,
            previewUrl = model.previewUrl,
            primaryGenreName = model.primaryGenreName,
            releaseDate = model.releaseDate,
            shortDescription = model.shortDescription,
            trackCensoredName = model.trackCensoredName,
            trackCount = model.trackCount,
            trackExplicitness = model.trackExplicitness,
            trackHdPrice = model.trackHdPrice,
            trackHdRentalPrice = model.trackHdRentalPrice,
            trackId = model.trackId,
            trackName = model.trackName,
            trackNumber = model.trackNumber,
            trackPrice = model.trackPrice,
            trackRentalPrice = model.trackRentalPrice,
            trackTimeMillis = model.trackTimeMillis,
            trackViewUrl = model.trackViewUrl,
            wrapperType = model.wrapperType
        )
    }

    override fun mapFromDomainModel(domainModel: ItunesMedia): ItunesMediaDto {
        return ItunesMediaDto(
            artistName = domainModel.artistName,
            artworkUrl100 = domainModel.artworkUrl100,
            artworkUrl30 = domainModel.artworkUrl30,
            artworkUrl60 = domainModel.artworkUrl60,
            collectionArtistId = domainModel.collectionArtistId,
            collectionArtistViewUrl = domainModel.collectionArtistViewUrl,
            collectionCensoredName = domainModel.collectionCensoredName,
            collectionExplicitness = domainModel.collectionExplicitness,
            collectionHdPrice = domainModel.collectionHdPrice,
            collectionId = domainModel.collectionId,
            collectionName = domainModel.collectionName,
            collectionPrice = domainModel.collectionPrice,
            collectionViewUrl = domainModel.collectionViewUrl,
            contentAdvisoryRating = domainModel.contentAdvisoryRating,
            country = domainModel.country,
            currency = domainModel.currency,
            discCount = domainModel.discCount,
            discNumber = domainModel.discNumber,
            hasITunesExtras = domainModel.hasITunesExtras,
            kind = domainModel.kind,
            longDescription = domainModel.longDescription,
            previewUrl = domainModel.previewUrl,
            primaryGenreName = domainModel.primaryGenreName,
            releaseDate = domainModel.releaseDate,
            shortDescription = domainModel.shortDescription,
            trackCensoredName = domainModel.trackCensoredName,
            trackCount = domainModel.trackCount,
            trackExplicitness = domainModel.trackExplicitness,
            trackHdPrice = domainModel.trackHdPrice,
            trackHdRentalPrice = domainModel.trackHdRentalPrice,
            trackId = domainModel.trackId,
            trackName = domainModel.trackName,
            trackNumber = domainModel.trackNumber,
            trackPrice = domainModel.trackPrice,
            trackRentalPrice = domainModel.trackRentalPrice,
            trackTimeMillis = domainModel.trackTimeMillis,
            trackViewUrl = domainModel.trackViewUrl,
            wrapperType = domainModel.wrapperType
        )
    }

    fun toDomainList(initial: List<ItunesMediaDto>): List<ItunesMedia> {
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<ItunesMedia>): List<ItunesMediaDto> {
        return initial.map { mapFromDomainModel(it) }
    }

}