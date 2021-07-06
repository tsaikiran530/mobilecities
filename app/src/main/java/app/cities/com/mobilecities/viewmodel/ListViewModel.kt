package app.cities.com.mobilecities.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import app.cities.com.mobilecities.RepoRepository
import app.cities.com.mobilecities.model.CitiesResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Sai Kiran on 03-07-2021.
 */
class ListViewModel @Inject constructor(private var repoRepository: RepoRepository) : DisposingViewModel() {

    private var citiesResponse:MutableLiveData<CitiesResponse>? = MutableLiveData<CitiesResponse>();

    fun fetchCities(context: Context) {
        addDisposable((repoRepository.getCities(context).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                 result -> setCitiesResponse(result) },
                 {onRetrievePostListError()}
        )))
    }


    fun getCitiesResponse() : MutableLiveData<CitiesResponse>?{
       return citiesResponse;
    }

     fun setCitiesResponse(citiesResponse: CitiesResponse){
         this.citiesResponse?.value = citiesResponse
    }

    private fun onRetrievePostListError(){
        Log.v("Not working"," Not working")
    }

}