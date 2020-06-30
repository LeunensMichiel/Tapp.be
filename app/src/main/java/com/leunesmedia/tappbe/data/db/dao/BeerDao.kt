package com.leunesmedia.tappbe.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.leunesmedia.tappbe.model.Beer
import kotlinx.coroutines.flow.Flow

@Dao
interface BeerDao {
    @Query("select * from beers order by name")
    fun getBeers(): Flow<List<Beer>>

    @Query("select * from beers where id = :id")
    fun getBeerById(id: String): Flow<Beer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(beers: List<Beer>)

    @Query("DELETE FROM beers")
    suspend fun deleteAll()
}