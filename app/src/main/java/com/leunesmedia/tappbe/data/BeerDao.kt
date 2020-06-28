package com.leunesmedia.tappbe.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface BeerDao {
    @Query("select * from beers order by name")
    fun getBeers(): LiveData<List<Beer>>

    @Query("select * from beers where id = :id")
    fun getBeerById(id: String): LiveData<Beer>
}