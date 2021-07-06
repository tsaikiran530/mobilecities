package app.cities.com.mobilecities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import app.cities.com.mobilecities.base.BaseActivity
import app.cities.com.mobilecities.model.CitiesResponseItem
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity


class MainActivity : BaseActivity(),ListFragment.OnItemClickListener {

    var listFragment: ListFragment? = null
    var mapFragment: MapFragment? = null
    override fun layoutRes(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null)
        listFragment = ListFragment()
        listFragment?.setOnItemClickListener(this)
            supportFragmentManager.beginTransaction().replace(R.id.screenContainer, listFragment!!).addToBackStack("list_fragment").commit()
    }


    override fun onClick(citiesResponseItem: CitiesResponseItem) {
        mapFragment = MapFragment.newInstance(citiesResponseItem);
        supportFragmentManager.beginTransaction().replace(R.id.screenContainer, mapFragment!!).addToBackStack("map_fragment").commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.getBackStackEntryCount() > 0)
            supportFragmentManager.popBackStackImmediate()
        else
            super.onBackPressed()
    }


}
