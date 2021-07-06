package app.cities.com.mobilecities.di

import app.cities.com.mobilecities.di.ViewModelModule
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Sai Kiran on 03-07-2021.
 */
@Singleton
@Module(includes = arrayOf(ViewModelModule::class))
class ServiceModule {

    @Singleton
    @Provides
    internal fun provideGson(): Gson {
        val gsonBuilder =  GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
        return gsonBuilder.create()
    }

}