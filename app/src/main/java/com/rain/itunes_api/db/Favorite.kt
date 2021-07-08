package com.rain.itunes_api.db

import androidx.room.Entity
import androidx.room.PrimaryKey

//Room database entity for favorite only saves id and name of itunes media
@Entity(tableName = "favorite_table")
class Favorite(
    @PrimaryKey
    val trackId: Int?,
    val trackName: String?,
)