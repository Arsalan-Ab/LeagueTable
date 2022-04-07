package com.example.leaguetable.api

import com.example.leaguetable.model.TableResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballApi {

    @GET("/api/v1/json/2/lookuptable.php?")
    suspend fun getLeagueTable(
        @Query("l")
        leagueId: String,
        @Query("s")
        season: String = "2021-2022"
    ): Response<TableResponse>

}