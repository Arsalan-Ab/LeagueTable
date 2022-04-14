package com.example.leaguetable.model

import com.google.gson.annotations.SerializedName

data class TableResponse(
    @SerializedName("table")
    val team: List<Team>
)