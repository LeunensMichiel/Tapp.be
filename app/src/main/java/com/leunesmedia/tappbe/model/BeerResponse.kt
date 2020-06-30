package com.leunesmedia.tappbe.model

class BeerResponse(
    val currentPage: Int,
    val numberOfPages: Int,
    val totalResults: Int,
    val data: List<Beer>
) {
}