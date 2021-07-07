package com.rain.itunes_api.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.rain.itunes_api.R
import com.rain.itunes_api.db.VisitDatabase
import com.rain.itunes_api.others.Constants.VISIT_DATABASE_NAME
import com.rain.itunes_api.ui.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

//A dagger hilt module for providing dependency injections all through out the application
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }

    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .placeholder(R.drawable.image_placeholder)
            .error(R.drawable.image_unavailable)
    )

    @Singleton
    @Provides
    fun provideVisitDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        VisitDatabase::class.java,
        VISIT_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun providesVisitDao(db: VisitDatabase) = db.getVisitDao()

}