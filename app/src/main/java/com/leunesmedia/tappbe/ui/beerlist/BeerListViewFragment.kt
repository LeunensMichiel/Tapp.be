package com.leunesmedia.tappbe.ui.beerlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.leunesmedia.tappbe.R
import com.leunesmedia.tappbe.adapters.BeerListAdapter
import com.leunesmedia.tappbe.model.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_beer_list_view.*

@AndroidEntryPoint
class BeerListView : Fragment() {
    private val beerListViewModel: BeerListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beer_list_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = BeerListAdapter(requireContext())
        recyclerview_beers.adapter = adapter
        recyclerview_beers.layoutManager = LinearLayoutManager(requireContext())
        recyclerview_beers.itemAnimator = DefaultItemAnimator()
        beerListViewModel.allBeers.observe(viewLifecycleOwner, Observer { result ->
            result?.let {
                when (result) {
                    is State.Success -> {
                        if (result.data.isNotEmpty()) {
                            adapter.setBeers(result.data.toMutableList())
                        }
                    }
                }
            }
        })
        if (beerListViewModel.allBeers.value !is State.Success) {
            beerListViewModel.getBeers()
        }
    }
}