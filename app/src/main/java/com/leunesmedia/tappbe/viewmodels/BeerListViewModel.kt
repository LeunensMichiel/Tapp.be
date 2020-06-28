package com.leunesmedia.tappbe.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.leunesmedia.tappbe.repositories.BeerRepository

class BeerListViewModel internal constructor(
    beerRepository: BeerRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val allBeers = beerRepository.getBeers()
}