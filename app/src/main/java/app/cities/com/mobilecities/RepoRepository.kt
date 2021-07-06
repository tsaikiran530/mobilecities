package app.cities.com.mobilecities

import android.content.Context
import app.cities.com.mobilecities.model.CitiesResponse
import com.google.gson.Gson
import javax.inject.Inject
import io.reactivex.Observable
import java.io.IOException


/**
 * Created by Sai Kiran on 03-07-2021.
 */
class RepoRepository @Inject constructor(private var gson:Gson){

    fun getCities(context:Context): Observable<CitiesResponse> {
        val jsonFileString = getJsonDataFromAsset(context, "cities.json")
        return  Observable.just(gson.fromJson(jsonFileString,  CitiesResponse::class.java));
    }

    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}