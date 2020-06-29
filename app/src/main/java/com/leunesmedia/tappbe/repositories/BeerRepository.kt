package com.leunesmedia.tappbe.repositories

import androidx.lifecycle.LiveData
import com.leunesmedia.tappbe.data.Beer
import com.leunesmedia.tappbe.data.BeerDao

class BeerRepository private constructor(private val beerDao: BeerDao) {
    fun getBeers() = beerDao.getBeers()
    fun getBeerById(beerId: String) = beerDao.getBeerById(beerId)
    suspend fun insert(beer: Beer) {
        beerDao.insert(beer)
    }

    companion object {
        @Volatile
        private var beerRepositoryInstance: BeerRepository? = null

        fun getBeerRepositoryInstance(beerDao: BeerDao) =
            beerRepositoryInstance ?: synchronized(this) {
                beerRepositoryInstance ?: BeerRepository(beerDao).also {
                    beerRepositoryInstance = it
                }
            }
    }
}