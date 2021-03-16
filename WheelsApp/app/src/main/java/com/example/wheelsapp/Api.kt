package com.example.wheelsapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "0c34e9bd09489eef34c93aaf9b05db4b",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>
    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String = "0c34e9bd09489eef34c93aaf9b05db4b",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>
    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String = "0c34e9bd09489eef34c93aaf9b05db4b",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>

    @GET("tv/popular")
    fun getPopularSeries(
        @Query("api_key") apiKey: String = "0c34e9bd09489eef34c93aaf9b05db4b",
        @Query("page") page: Int
    ): Call<GetSeriesResponse>

    @GET("tv/top_rated")
    fun getTopRatedSeries(
        @Query("api_key") apiKey: String = "0c34e9bd09489eef34c93aaf9b05db4b",
        @Query("page") page: Int
    ): Call<GetSeriesResponse>

    @GET("tv/on_the_air")
    fun getOnAirSeries(
        @Query("api_key") apiKey: String = "0c34e9bd09489eef34c93aaf9b05db4b",
        @Query("page") page: Int
    ): Call<GetSeriesResponse>

    @GET("tv/airing_today")
    fun getAiringTodaySeries(
        @Query("api_key") apiKey: String = "0c34e9bd09489eef34c93aaf9b05db4b",
        @Query("page") page: Int
    ): Call<GetSeriesResponse>
}