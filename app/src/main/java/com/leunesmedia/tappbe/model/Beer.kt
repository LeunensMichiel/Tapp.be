package com.leunesmedia.tappbe.model

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.leunesmedia.tappbe.model.Beer.Companion.TABLE_NAME
import com.squareup.moshi.Json

@Entity(tableName = TABLE_NAME)
data class Beer(
    @Json(name = "id") @PrimaryKey @ColumnInfo(name = "id") val beerId: String,
    val name: String? = null,
    val description: String? = null,
    val price: Double? = 0.0,
    val volume: Double? = 0.0
) {
    companion object {
        const val TABLE_NAME = "beers"
    }

    override fun toString() = "$name $volume"
}