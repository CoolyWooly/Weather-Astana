package kz.weatherastana.app.data.cloud.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kz.weatherastana.app.data.cloud.ResultWrapper
import kz.weatherastana.app.data.cloud.rest.ApiService
import kz.weatherastana.app.data.cloud.safeApiCall
import kz.weatherastana.app.domain.model.WeatherResponse

class CloudRepository(
    private val apIs: ApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseCloudRepository {

    override suspend fun getWeather(
        cnt: Int
    ): ResultWrapper<WeatherResponse> {
        return safeApiCall(dispatcher) {
            apIs.getWeather(1526273, cnt, "60553868a81454e78c8d291284b51855")
        }
    }
}
