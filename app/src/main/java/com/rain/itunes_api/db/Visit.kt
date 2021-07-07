package com.rain.itunes_api.db

import androidx.room.Entity
import androidx.room.PrimaryKey

//Room database entity for Visit object
@Entity(tableName = "visit_table")
data class Visit(
    var timestamp: Long = 0L
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
