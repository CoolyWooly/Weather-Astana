package kz.weatherastana.app.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kz.weatherastana.app.ui.home.HomeFragment

@Module
abstract class MainActivityProviders {

    @ContributesAndroidInjector
    abstract fun provideHomeFragment(): HomeFragment

}