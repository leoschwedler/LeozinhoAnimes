package com.example.leozinhoanimes.model

import com.google.gson.annotations.SerializedName

data class TopAnimes(
    @SerializedName("data")
    val DateAnimes: List<DateAnimes>,
    @SerializedName("pagination")
    val pagination: Pagination
)