package app.cities.com.mobilecities.di

import app.cities.com.mobilecities.di.DaggerApplicationComponent
import dagger.android.DaggerApplication

/**
 * Created by Sai Kiran on 03-07-2021.
 */
class BaseApplication : DaggerApplication() {
    private val applicationInjector = DaggerApplicationComponent.builder()
            .application(this)
            .build()

    override fun applicationInjector() = applicationInjector
}