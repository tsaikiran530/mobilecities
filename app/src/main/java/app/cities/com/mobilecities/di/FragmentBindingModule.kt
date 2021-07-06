package app.cities.com.mobilecities.di

import app.cities.com.mobilecities.ListFragment
import app.cities.com.mobilecities.MapFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Ramana on 16-09-2020.
 */

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    internal abstract fun provideListFragment(): ListFragment

    @ContributesAndroidInjector
    internal abstract fun provideMapFragment(): MapFragment
}