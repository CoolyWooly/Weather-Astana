package kz.weatherastana.app.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kz.weatherastana.app.ui.home.HomeFragment
import kz.weatherastana.app.ui.home.details.HomeDetailsFragment

@Module
abstract class MainActivityProviders {

    @ContributesAndroidInjector
    abstract fun provideHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun provideHomeDetailsFragment(): HomeDetailsFragment

}