package app.cities.com.mobilecities.di

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import app.cities.com.mobilecities.viewmodel.ListViewModel
import app.cities.com.mobilecities.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

/**
 * Created by Sai Kiran on 03-07-2021.
 */
@Singleton
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    internal abstract fun bindListViewModel(listViewModel: ListViewModel): ViewModel

    @Binds
    internal abstract fun provideContext(application: Application): Context

}