package com.leunesmedia.tappbe.utils

import android.content.Context
import androidx.fragment.app.Fragment
import com.leunesmedia.tappbe.data.TappDatabase
import com.leunesmedia.tappbe.repositories.BeerRepository
import com.leunesmedia.tappbe.viewmodels.BeerListViewModel
import com.leunesmedia.tappbe.viewmodels.BeerListViewModelFactory

object InjectorUtils {
    private fun getBeerRepository(context: Context): BeerRepository {
        return BeerRepository.getBeerRepositoryInstance(
            TappDatabase.getTappDatabaseInstance(context.applicationContext).beerDao()
        )
    }
    fun provideBeerListViewModelFactory(context: Context): BeerListViewModelFactory {
        return BeerListViewModelFactory(getBeerRepository(context.applicationContext))
    }
}