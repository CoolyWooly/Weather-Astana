package kz.weatherastana.app.data.cloud.rest

import kz.weatherastana.app.domain.model.WeatherResponse
import retrofit2.http.GET

interface ApiService {

    @GET("forecast/81285ced59f7eb13d017d80588ecfe93/51.169392,71.449074?lang=ru")
    suspend fun getWeather(): WeatherResponse
}