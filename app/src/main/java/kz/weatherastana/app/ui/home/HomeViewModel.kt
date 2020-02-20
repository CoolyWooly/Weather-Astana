package kz.weatherastana.app.ui.home

import androidx.lifecycle.MutableLiveData
import kz.weatherastana.app.core.BaseViewModel
import kz.weatherastana.app.data.cloud.ResultWrapper
import kz.weatherastana.app.data.cloud.repository.BaseCloudRepository
import kz.weatherastana.app.domain.model.DayModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val baseCloudRepository: BaseCloudRepository
) : BaseViewModel() {

    private val TAG = this::class.java.simpleName
    val isRefreshing: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>(false) }
    val daysData: MutableLiveData<List<DayModel>> by lazy { MutableLiveData<List<DayModel>>() }
    val error: MutableLiveData<ResultWrapper.Error> by lazy { MutableLiveData<ResultWrapper.Error>() }

    init {
        getWeather()
    }

    fun getWeather() {
        launchIO {
            isRefreshing.postValue(true)
            val weatherResponse = baseCloudRepository.getWeather()
            when (weatherResponse) {
                is ResultWrapper.Error -> error.postValue(weatherResponse)
                is ResultWrapper.Success -> daysData.postValue(weatherResponse.value.daily.data)
            }
            isRefreshing.postValue(false)
        }
    }
}