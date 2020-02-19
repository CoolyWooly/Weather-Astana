package kz.weatherastana.app.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import kz.weatherastana.app.R

@BindingAdapter("image_status")
fun imageStatus(imageView: ImageView, status: String) {
    val drawable = when(status) {
        "clear-day" -> R.drawable.day_clear
        "clear-night" -> R.drawable.night_half_moon_clear
        "rain" -> R.drawable.rain
        "snow" -> R.drawable.snow
        "sleet" -> R.drawable.sleet
        "wind" -> R.drawable.wind
        "fog" -> R.drawable.fog
        "cloudy" -> R.drawable.cloudy
        "partly-cloudy-day" -> R.drawable.day_partial_cloud
        "partly-cloudy-night" -> R.drawable.night_half_moon_partial_cloud
        else -> R.drawable.angry_clouds
    }

    Glide
        .with(imageView.context)
        .load(drawable)
        .into(imageView)
}

@BindingAdapter("is_refreshing")
fun isRefreshing(swipeRefreshLayout: SwipeRefreshLayout, boolean: Boolean) {
    swipeRefreshLayout.isRefreshing = boolean
}