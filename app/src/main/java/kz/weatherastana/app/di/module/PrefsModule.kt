package kz.weatherastana.app.di.module

import dagger.Binds
import dagger.Module
import kz.weatherastana.app.data.db.Prefs
import kz.weatherastana.app.data.db.PrefsImpl

@Module
abstract class PrefsModule {

    @Binds
    abstract fun bindPrefsManager(prefsImpl: PrefsImpl): Prefs
}