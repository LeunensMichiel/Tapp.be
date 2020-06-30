package com.leunesmedia.tappbe.data.api

import com.leunesmedia.tappbe.model.Beer
import com.leunesmedia.tappbe.model.BeerResponse
import retrofit2.Response
import retrofit2.http.GET

interface BeerService {
    @GET("beers?p=2&key=d115efde689dbb22d9b41ce673189300")
    suspend fun getBeers(): Response<BeerResponse>
}