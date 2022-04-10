package com.example.leaguetable.repository

import com.example.leaguetable.api.FootballApi
import javax.inject.Inject

class FootballRepository @Inject constructor(
    val api: FootballApi
) {
    suspend fun getLeagueTable(leagueId: String, season: String) {
        api.getLeagueTable(leagueId, season)
    }

    suspend fun getPlayerByName(playerName: String) {
        api.getPlayerByName(playerName)
    }
}