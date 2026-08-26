package com.example.weatherapp.search.data.remote

import com.example.weatherapp.search.data.model.SearchResultDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchResultApi {
    @GET("search.json")
    suspend fun searchResultResponse(
        @Query("q") cityName: String
    ): List<SearchResultDto>
}