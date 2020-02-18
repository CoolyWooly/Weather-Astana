package kz.weatherastana.app.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kz.weatherastana.app.ui.MainActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityProviders::class])
    abstract fun bindMainActivity(): MainActivity

}