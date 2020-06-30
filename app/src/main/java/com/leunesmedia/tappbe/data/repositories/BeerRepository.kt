package com.leunesmedia.tappbe.data.repositories

import androidx.annotation.MainThread
import com.leunesmedia.tappbe.data.api.BeerService
import com.leunesmedia.tappbe.model.Beer
import com.leunesmedia.tappbe.data.db.dao.BeerDao
import com.leunesmedia.tappbe.model.BeerResponse
import com.leunesmedia.tappbe.model.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeerRepository @Inject internal constructor(
    private val beerDao: BeerDao,
    private val beerService: BeerService
) {
    @ExperimentalCoroutinesApi
    fun getBeers(): Flow<State<List<Beer>>> {
        return object : NetworkBoundRepository<List<Beer>, BeerResponse>() {
            override suspend fun saveRemoteData(response: BeerResponse) =
                beerDao.insert(response.data)

            override fun fetchFromLocal(): Flow<List<Beer>> = beerDao.getBeers()

            override suspend fun fetchFromRemote(): Response<BeerResponse> = beerService.getBeers()
        }.asFlow().flowOn(Dispatchers.IO)
    }

    @MainThread
    fun getBeerById(beerId: String) = beerDao.getBeerById(beerId)

}