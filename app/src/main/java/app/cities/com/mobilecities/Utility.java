package app.cities.com.mobilecities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import app.cities.com.mobilecities.model.CitiesResponseItem;

/**
 * Created by Ramana on 04-07-2021.
 */

public class Utility {
    static List<CitiesResponseItem> binarySearch(List<CitiesResponseItem> citiesResponseItems, String constraint) {
        List<CitiesResponseItem> citySorted = new ArrayList<>();
        boolean firstOccurance = false;
        for (CitiesResponseItem item : citiesResponseItems) {
            if (item.getName().toLowerCase().startsWith(constraint.toLowerCase())) {
                citySorted.add(item);
                firstOccurance = true;
            } else {
                if (firstOccurance) {
                    return citySorted;
                }
            }
        }

        return citySorted;
    }
}
