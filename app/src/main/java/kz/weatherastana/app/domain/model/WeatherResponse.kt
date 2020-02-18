package kz.weatherastana.app.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WeatherResponse(
    @SerializedName("cod") val cod: Int?,
    @SerializedName("list") val list: List<DayModel>?
) : Serializable