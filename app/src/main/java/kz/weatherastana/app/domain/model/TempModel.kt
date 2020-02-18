package kz.weatherastana.app.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TempModel(
    @SerializedName("day") val day: Double?,
    @SerializedName("min") val min: Double?,
    @SerializedName("max") val max: Double?,
    @SerializedName("night") val night: Double?,
    @SerializedName("eve") val eve: Double?,
    @SerializedName("morn") val morn: Double?
) : Serializable