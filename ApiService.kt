package com.example.pogoda

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/inf/meteo.php?tid=8")
    fun fetchAllWeathers(): Call<List<Description>>
}