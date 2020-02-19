package kz.weatherastana.app.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

data class DayModel (
    @SerializedName("time") val time : Int,
    @SerializedName("summary") val summary : String,
    @SerializedName("icon") val icon : String,
    @SerializedName("sunriseTime") val sunriseTime : Int,
    @SerializedName("sunsetTime") val sunsetTime : Int,
    @SerializedName("moonPhase") val moonPhase : Double,
    @SerializedName("precipIntensity") val precipIntensity : Double,
    @SerializedName("precipIntensityMax") val precipIntensityMax : Double,
    @SerializedName("precipIntensityMaxTime") val precipIntensityMaxTime : Int,
    @SerializedName("precipProbability") val precipProbability : Double,
    @SerializedName("precipType") val precipType : String,
    @SerializedName("precipAccumulation") val precipAccumulation : Double,
    @SerializedName("temperatureHigh") val temperatureHigh : Double,
    @SerializedName("temperatureHighTime") val temperatureHighTime : Int,
    @SerializedName("temperatureLow") val temperatureLow : Double,
    @SerializedName("temperatureLowTime") val temperatureLowTime : Int,
    @SerializedName("apparentTemperatureHigh") val apparentTemperatureHigh : Double,
    @SerializedName("apparentTemperatureHighTime") val apparentTemperatureHighTime : Int,
    @SerializedName("apparentTemperatureLow") val apparentTemperatureLow : Double,
    @SerializedName("apparentTemperatureLowTime") val apparentTemperatureLowTime : Int,
    @SerializedName("dewPoint") val dewPoint : Double,
    @SerializedName("humidity") val humidity : Double,
    @SerializedName("pressure") val pressure : Double,
    @SerializedName("windSpeed") val windSpeed : Double,
    @SerializedName("windGust") val windGust : Double,
    @SerializedName("windGustTime") val windGustTime : Int,
    @SerializedName("windBearing") val windBearing : Int,
    @SerializedName("cloudCover") val cloudCover : Double,
    @SerializedName("uvIndex") val uvIndex : Int,
    @SerializedName("uvIndexTime") val uvIndexTime : Int,
    @SerializedName("visibility") val visibility : Double,
    @SerializedName("ozone") val ozone : Double,
    @SerializedName("temperatureMin") val temperatureMin : Double,
    @SerializedName("temperatureMinTime") val temperatureMinTime : Int,
    @SerializedName("temperatureMax") val temperatureMax : Double,
    @SerializedName("temperatureMaxTime") val temperatureMaxTime : Int,
    @SerializedName("apparentTemperatureMin") val apparentTemperatureMin : Double,
    @SerializedName("apparentTemperatureMinTime") val apparentTemperatureMinTime : Int,
    @SerializedName("apparentTemperatureMax") val apparentTemperatureMax : Double,
    @SerializedName("apparentTemperatureMaxTime") val apparentTemperatureMaxTime : Int
) : Serializable {

    fun getDate() : String {
        val date = Date(time * 1000L)
        val jdf = SimpleDateFormat("dd.MM EEEE", Locale.getDefault())
        return jdf.format(date)
    }

    fun getSunrise() : String {
        val date = Date(sunriseTime * 1000L)
        val jdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        return jdf.format(date)
    }

    fun getSunset() : String {
        val date = Date(sunsetTime * 1000L)
        val jdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        return jdf.format(date)
    }

    fun getTemperatureHighCel() : String {
        return "${getCel(temperatureHigh)}°C"
    }

    fun getTemperatureApparentHighCel() : String {
        return "${getCel(apparentTemperatureHigh)}°C"
    }

    fun getTemperatureMinCel() : String {
        return "${getCel(temperatureLow)}°C"
    }

    fun getTemperatureApparentMinCel() : String {
        return "${getCel(apparentTemperatureLow)}°C"
    }

    fun getWindMetersSec() : String {
        return "${String.format("%.1f", windGust / 2.263)} м/с"
    }

    fun getMoon() : String {
        return "${(moonPhase * 100).roundToInt()}%"
    }

    private fun getCel(fah: Double) : Int {
        return ((fah - 32.0) * 5/9).roundToInt()
    }
}