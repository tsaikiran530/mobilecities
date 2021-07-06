package app.cities.com.mobilecities.di


import app.cities.com.mobilecities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Ramana on 16-09-2020.
 */
@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = arrayOf(FragmentBindingModule::class))
    internal abstract fun bindMainActivity(): MainActivity
}