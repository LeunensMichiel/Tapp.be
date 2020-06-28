package com.leunesmedia.tappbe.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "beers")
data class Beer(
    @PrimaryKey @ColumnInfo(name = "id") val beerId: String,
    val name: String,
    val description: String,
    val price: Double,
    val volume: Double
) {

    override fun toString() = "$name $volume"
}