package com.leunesmedia.tappbe.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leunesmedia.tappbe.data.Beer
import com.leunesmedia.tappbe.repositories.BeerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BeerListViewModel internal constructor(
    private val beerRepository: BeerRepository
) : ViewModel() {
    val allBeers : LiveData<List<Beer>> = beerRepository.getBeers()
    fun insert(beer: Beer) {
        viewModelScope.launch {
            beerRepository.insert(beer)
        }
    }
}