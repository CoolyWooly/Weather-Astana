package kz.weatherastana.app.domain.model

import com.google.gson.annotations.SerializedName

data class DailyModel (
    @SerializedName("data") val data : List<DayModel>
)