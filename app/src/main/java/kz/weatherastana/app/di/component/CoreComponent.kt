package kz.weatherastana.app.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import kz.weatherastana.app.core.App
import kz.weatherastana.app.di.builder.ActivityBuilder
import kz.weatherastana.app.di.module.ContextModule
import kz.weatherastana.app.di.module.NetworkModule
import kz.weatherastana.app.di.module.PrefsModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    NetworkModule::class,
    ActivityBuilder::class,
    ContextModule::class,
    PrefsModule::class
])
interface CoreComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): CoreComponent
    }
}