package app.cities.com.mobilecities.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CitiesResponseItem(
        val _id: Int,
        val coord: Coord,
        val country: String,
        val name: String
):Parcelable