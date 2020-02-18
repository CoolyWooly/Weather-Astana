package kz.weatherastana.app.data.cloud.repository

import kz.weatherastana.app.data.cloud.ResultWrapper
import kz.weatherastana.app.domain.model.WeatherResponse

interface BaseCloudRepository {

    suspend fun getWeather(): ResultWrapper<WeatherResponse>

}