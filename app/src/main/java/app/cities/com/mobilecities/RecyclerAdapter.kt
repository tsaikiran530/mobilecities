package app.cities.com.mobilecities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import app.cities.com.mobilecities.model.CitiesResponseItem
import kotlinx.android.synthetic.main.list_layout.view.*
import java.util.*
import java.util.stream.Collectors

/**
 * Created by Ramana on 04-07-2021.
 */
class RecyclerAdapter(val cityList: List<CitiesResponseItem>,val listener:onCityInterface): RecyclerView.Adapter<RecyclerAdapter.CityHolder>(),Filterable  {

    var countryFilterList = ArrayList<CitiesResponseItem>()

    init {
        countryFilterList = cityList as ArrayList<CitiesResponseItem>
    }
    override fun getItemCount(): Int {
        return countryFilterList.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.CityHolder, position: Int) {
        holder.bindItems(countryFilterList[position],listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int):  RecyclerAdapter.CityHolder{
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return CityHolder(v)
    }

    //the class is hodling the list view
    class CityHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val cityName = itemView.cityName
        private val cityCor = itemView.city_co_ordinates


        fun bindItems(citiesResponseItem: CitiesResponseItem,listener: onCityInterface) {
           cityName.text = "Name : ".plus(citiesResponseItem.name).plus(" \t").plus("Country :").plus(citiesResponseItem.country);
            cityCor.text = "Lat : ".plus(citiesResponseItem.coord.lat.toString()).plus("\t").plus("Long :").plus(citiesResponseItem.coord.lon.toString());


            itemView.setOnClickListener(View.OnClickListener {
                if(listener != null){
                    listener.onCityClick(citiesResponseItem)
                }
            })

        }


    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    countryFilterList = cityList as ArrayList<CitiesResponseItem>
                } else {
                    countryFilterList = Utility.binarySearch(cityList,charSearch) as ArrayList<CitiesResponseItem>;
                }
                val filterResults = FilterResults()
                filterResults.values = countryFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                countryFilterList = results?.values as ArrayList<CitiesResponseItem>
                notifyDataSetChanged()
            }
        }
    }
}