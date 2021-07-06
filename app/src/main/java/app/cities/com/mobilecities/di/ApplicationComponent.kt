package app.cities.com.mobilecities.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Sai Kiran on 03-07-2021.
 */
@Singleton
@Component(modules = arrayOf(ServiceModule::class,AndroidSupportInjectionModule::class, ActivityBindingModule::class))
interface ApplicationComponent : AndroidInjector<BaseApplication> {
    override fun inject(application: BaseApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: BaseApplication): Builder

        fun build(): ApplicationComponent
    }
}