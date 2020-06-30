package com.leunesmedia.tappbe.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.leunesmedia.tappbe.R
import com.leunesmedia.tappbe.model.Beer

class BeerListAdapter internal constructor(context: Context) : RecyclerView.Adapter<BeerListAdapter.BeerViewHolder>(){
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var beers = emptyList<Beer>()

    inner class BeerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val beerItemView: TextView = itemView.findViewById(R.id.recyclerView_item_beer_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item_beer, parent, false)
        return BeerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        val current = beers[position]
        holder.beerItemView.text = current.name
    }

    override fun getItemCount(): Int {
        return beers.size
    }

    internal fun setBeers(beers: List<Beer>) {
        this.beers = beers
        notifyDataSetChanged()
    }
}