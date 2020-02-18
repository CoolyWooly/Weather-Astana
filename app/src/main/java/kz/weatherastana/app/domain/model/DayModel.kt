package kz.weatherastana.app.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DayModel(
    @SerializedName("dt") val dt: Int?,
    @SerializedName("temp") val temp: TempModel?,
    @SerializedName("pressure") val pressure: Double?,
    @SerializedName("humidity") val humidity: Int?,
    @SerializedName("weather") val weather: List<WeatherModel>?,
    @SerializedName("speed") val speed: Double?,
    @SerializedName("deg") val deg: Int?,
    @SerializedName("clouds") val clouds: Int?,
    @SerializedName("snow") val snow: Double?
) : Serializable