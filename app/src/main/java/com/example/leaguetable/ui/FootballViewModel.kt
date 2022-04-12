package com.example.leaguetable.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leaguetable.model.TableResponse
import com.example.leaguetable.repository.FootballRepository
import com.example.leaguetable.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class FootballViewModel @Inject constructor(
    val repository: FootballRepository
) : ViewModel() {

    val tables: MutableLiveData<Resource<TableResponse>> = MutableLiveData()

    fun getLeagueTable(leagueId: String, season: String) = viewModelScope.launch {
        tables.postValue(Resource.Loading())
        val response = repository.getLeagueTable(leagueId, season)
        tables.postValue(handleTablesResponse(response))
    }

    private fun handleTablesResponse(response: Response<TableResponse>): Resource<TableResponse> {
        if (response.isSuccessful) {
            response.body()?.let { tableResponse ->
                return Resource.Success(tableResponse)
            }
        }
        return Resource.Error(response.message())
    }
}