package app.cities.com.mobilecities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.cities.com.mobilecities.base.BaseFragment
import app.cities.com.mobilecities.model.CitiesResponseItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.MapsInitializer
import android.widget.ProgressBar
import com.google.android.gms.maps.MapView

/**
 * Created by Ramana on 06-07-2021.
 */
class MapFragment: BaseFragment(), OnMapReadyCallback {

    private lateinit var googleMap:GoogleMap
    private lateinit var citiesResponseItem: CitiesResponseItem
    var mLoadingView: ProgressBar? = null
    var mMapView: MapView? = null

    companion object {
        const val CITI_RESPONSE_ITEM = "city_response_item"
        fun newInstance(citiesResponseItem: CitiesResponseItem): MapFragment {
            val fragment = MapFragment()

            val bundle = Bundle().apply {
                putParcelable(CITI_RESPONSE_ITEM, citiesResponseItem)
            }

            fragment.arguments = bundle
            return fragment
        }
    }

    override fun layoutRes(): Int {
        return R.layout.fragment_map
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            citiesResponseItem = bundle.getParcelable(CITI_RESPONSE_ITEM)!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_map, container, false)
        mLoadingView = rootView.findViewById(R.id.loading_view);
        mMapView = rootView.findViewById(R.id.mapView);

        mLoadingView?.visibility = View.VISIBLE
        mMapView?.onCreate(savedInstanceState)
        mMapView?.onResume()
        mMapView?.getMapAsync(this)
        try {
            MapsInitializer.initialize(activity!!.applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onMapReady(map: GoogleMap?) {
        map?.let {
            googleMap = it
        }
        val latLong = LatLng(citiesResponseItem.coord.lat, citiesResponseItem.coord.lon)
        googleMap.addMarker(MarkerOptions()
                .position(latLong)
                .title("Marker in" +" "+ citiesResponseItem.name))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLong))
        mLoadingView?.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        mMapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMapView?.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView?.onLowMemory()
    }
}