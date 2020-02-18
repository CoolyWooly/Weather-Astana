package kz.weatherastana.app.di.builder

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kz.weatherastana.app.di.qualifier.ViewModelKey
import kz.weatherastana.app.ui.home.HomeViewModel
import kz.weatherastana.app.ui.home.details.HomeDetailsViewModel

@Module
abstract class AppViewModelBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeDetailsViewModel::class)
    abstract fun bindHomeDetailsViewModel(homeDetailsViewModel: HomeDetailsViewModel): ViewModel

}