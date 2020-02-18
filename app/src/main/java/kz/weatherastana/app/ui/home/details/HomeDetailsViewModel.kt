package kz.weatherastana.app.ui.home.details

import androidx.lifecycle.MutableLiveData
import kz.weatherastana.app.core.BaseViewModel
import kz.weatherastana.app.data.cloud.ResultWrapper
import kz.weatherastana.app.data.cloud.repository.BaseCloudRepository
import kz.weatherastana.app.data.db.PrefsImpl
import javax.inject.Inject

class HomeDetailsViewModel @Inject constructor(
    private val baseCloudRepository: BaseCloudRepository,
    val prefsImpl: PrefsImpl
) : BaseViewModel() {
    private val TAG = this::class.java.simpleName

//    val ratingModelData: MutableLiveData<RatingModel> by lazy { MutableLiveData<RatingModel>() }
    val error: MutableLiveData<ResultWrapper.Error> by lazy { MutableLiveData<ResultWrapper.Error>() }
}