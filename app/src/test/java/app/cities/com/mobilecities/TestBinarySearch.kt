package app.cities.com.mobilecities

import app.cities.com.mobilecities.model.CitiesResponseItem
import app.cities.com.mobilecities.model.Coord
import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * Created by Ramana on 06-07-2021.
 */
class TestBinarySearch {

   var citiesResponseList: List<CitiesResponseItem>? = null
    var resultResponseList: List<CitiesResponseItem>? = null

    @Test
    fun shouldSortBySearchQuery() {
        citiesResponseList = ArrayList();
        resultResponseList = ArrayList();
        val citiesResponseItem1 = CitiesResponseItem(1, Coord(0.5,0.5),"PH","Alupka")
        (citiesResponseList as ArrayList<CitiesResponseItem>).add(citiesResponseItem1);
        val citiesResponseItem2 = CitiesResponseItem(1, Coord(0.5,0.5),"PH","Alupay")
        (citiesResponseList as ArrayList<CitiesResponseItem>).add(citiesResponseItem2);
        val citiesResponseItem3 = CitiesResponseItem(1, Coord(0.5,0.5),"PH","A Estrada")
        (citiesResponseList as ArrayList<CitiesResponseItem>).add(citiesResponseItem3);
        val citiesResponseItem4 = CitiesResponseItem(1, Coord(0.5,0.5),"PH","sachsenhausen")
        (citiesResponseList as ArrayList<CitiesResponseItem>).add(citiesResponseItem4);
        val citiesResponseItem5 = CitiesResponseItem(1, Coord(0.5,0.5),"PH","ka bang")
        (citiesResponseList as ArrayList<CitiesResponseItem>).add(citiesResponseItem5);
        val citiesResponseItem6 = CitiesResponseItem(1, Coord(0.5,0.5),"PH","vaale")
        (citiesResponseList as ArrayList<CitiesResponseItem>).add(citiesResponseItem6);
        val citiesResponseItem7 = CitiesResponseItem(1, Coord(0.5,0.5),"PH","rabastens")
        (citiesResponseList as ArrayList<CitiesResponseItem>).add(citiesResponseItem7);
        val citiesResponseItem8 = CitiesResponseItem(1, Coord(0.5,0.5),"PH","haar")
        (citiesResponseList as ArrayList<CitiesResponseItem>).add(citiesResponseItem8);

        (resultResponseList as ArrayList<CitiesResponseItem>).add(citiesResponseItem1);
        assertEquals(resultResponseList, Utility.binarySearch(citiesResponseList,"Alupk"));

        assertEquals(ArrayList<CitiesResponseItem>(), Utility.binarySearch(citiesResponseList,"roice"));

    }
}