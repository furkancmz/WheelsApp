package com.example.wheelsapp


import com.google.gson.annotations.SerializedName
import java.io.Serializable
class GetSeriesResponse (
    @SerializedName("page") val page: Int,
    @SerializedName("results") val series: List<Serie>,
    @SerializedName("total_pages") val pages: Int
    )