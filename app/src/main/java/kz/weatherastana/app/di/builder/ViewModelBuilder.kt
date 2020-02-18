package kz.weatherastana.app.di.builder

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import kz.weatherastana.app.di.module.PrefsModule
import kz.weatherastana.app.ui.ViewModelFactory

@Module(includes = [PrefsModule::class, AppViewModelBuilder::class])
abstract class ViewModelBuilder {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}