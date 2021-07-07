package com.rain.itunes_api.di

import com.rain.itunes_api.network.ApiService
import com.rain.itunes_api.network.model.ItunesMediaDtoMapper
import com.rain.itunes_api.repository.ItunesMediaRepository
import com.rain.itunes_api.repository.ItunesMediaRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

//A dagger hilt module for providing dependency injections of repositories
@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideItunesMediaRepository(
        apiService: ApiService,
        itunesMediaDtoMapper: ItunesMediaDtoMapper
    ): ItunesMediaRepository {
        return ItunesMediaRepositoryImpl(apiService, itunesMediaDtoMapper)
    }

}