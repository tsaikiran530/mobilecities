package app.cities.com.mobilecities

import android.os.Bundle
import app.cities.com.mobilecities.model.CitiesResponseItem

/**
 * Created by Ramana on 05-07-2021.
 */
interface onCityInterface {

    fun onCityClick(citiesResponseItem: CitiesResponseItem);
}