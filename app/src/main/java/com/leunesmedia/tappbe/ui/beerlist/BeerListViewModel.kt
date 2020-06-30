package com.leunesmedia.tappbe.ui.beerlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leunesmedia.tappbe.model.Beer
import com.leunesmedia.tappbe.data.repositories.BeerRepository
import com.leunesmedia.tappbe.model.State
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BeerListViewModel @ViewModelInject internal constructor(
    private val beerRepository: BeerRepository
) : ViewModel() {

    private val _allBeers = MutableLiveData<State<List<Beer>>>()
    val allBeers: LiveData<State<List<Beer>>> get() = _allBeers

    fun getBeers() {
        viewModelScope.launch {
            beerRepository.getBeers().collect{
                _allBeers.value = it
            }
        }
    }
}