package kz.weatherastana.app.ui

import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import kz.weatherastana.app.R
import kz.weatherastana.app.domain.model.WeatherResponse

@BindingAdapter("image_url")
fun loadImage(imageView: ImageView, url: String?) {
    Glide
        .with(imageView.context)
        .load(url)
        .placeholder(R.drawable.ic_image)
        .into(imageView)
}

@BindingAdapter("image_url")
fun loadImage(imageView: ImageView, weatherResponse: WeatherResponse) {
//    if (weatherResponse.images.isNullOrEmpty()) {
//        Glide
//            .with(imageView.context)
//            .load(R.drawable.ic_image)
//            .into(imageView)
//    } else {
//        Glide
//            .with(imageView.context)
//            .load(weatherResponse.images[0].url)
//            .placeholder(R.drawable.ic_image)
//            .into(imageView)
//    }
}

@BindingAdapter("set_html")
fun setHtml(textView: TextView, text: String?) {
    if (text != null) {
        textView.text = Html.fromHtml(text)
    }
}

@BindingAdapter("is_refreshing")
fun isRefreshing(swipeRefreshLayout: SwipeRefreshLayout, boolean: Boolean) {
    swipeRefreshLayout.isRefreshing = boolean
}