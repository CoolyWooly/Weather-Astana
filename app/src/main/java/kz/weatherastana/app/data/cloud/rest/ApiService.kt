package kz.weatherastana.app.data.cloud.rest

import kz.weatherastana.app.domain.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("id") id: Int,
        @Query("cnt") cnt: Int,
        @Query("appid") appid: String
    ): WeatherResponse
}