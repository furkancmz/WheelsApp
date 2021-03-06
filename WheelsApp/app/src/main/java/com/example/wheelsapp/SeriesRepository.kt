package com.example.wheelsapp

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SeriesRepository {

    val api: Api

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(Api::class.java)
    }
    fun getPopularSeries(
        page: Int = 1,
        onSuccess: (series: List<Serie>) -> Unit,
        onError: () -> Unit
    ) {
        api.getPopularSeries(page = page)
            .enqueue(object : Callback<GetSeriesResponse> {
                override fun onFailure(call: Call<GetSeriesResponse>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(
                    call: Call<GetSeriesResponse>,
                    response: Response<GetSeriesResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.series)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }


            })
    }
    fun getTopRatedSeries(
        page: Int = 1,
        onSuccess: (series: List<Serie>) -> Unit,
        onError: () -> Unit
    ) {
        api.getTopRatedSeries(page = page)
            .enqueue(object : Callback<GetSeriesResponse> {
                override fun onResponse(
                    call: Call<GetSeriesResponse>,
                    response: Response<GetSeriesResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.series)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetSeriesResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }
    fun getAiringTodaySeries(
        page: Int = 1,
        onSuccess: (series: List<Serie>) -> Unit,
        onError: () -> Unit
    ) {
        api.getAiringTodaySeries(page = page)
            .enqueue(object : Callback<GetSeriesResponse> {
                override fun onResponse(
                    call: Call<GetSeriesResponse>,
                    response: Response<GetSeriesResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.series)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetSeriesResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }
    fun getOnAirSeries(
        page: Int = 1,
        onSuccess: (series: List<Serie>) -> Unit,
        onError: () -> Unit
    ) {
        api.getOnAirSeries(page = page)
            .enqueue(object : Callback<GetSeriesResponse> {
                override fun onResponse(
                    call: Call<GetSeriesResponse>,
                    response: Response<GetSeriesResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.series)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetSeriesResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }
}