package com.example.leaguetable.repository

import com.example.leaguetable.api.FootballApi
import javax.inject.Inject

class FootballRepository @Inject constructor(
    val api: FootballApi
) {
    suspend fun getLeagueTable(leagueId: String) =
        api.getLeagueTable(leagueId)


    suspend fun getPlayerByName(playerName: String) =
        api.getPlayerByName(playerName)

}