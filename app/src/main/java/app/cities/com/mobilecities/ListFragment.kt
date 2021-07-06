package app.cities.com.mobilecities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.cities.com.mobilecities.base.BaseFragment
import app.cities.com.mobilecities.databinding.ScreenListBinding
import app.cities.com.mobilecities.model.CitiesResponse
import app.cities.com.mobilecities.model.CitiesResponseItem
import app.cities.com.mobilecities.viewmodel.ListViewModel
import app.cities.com.mobilecities.viewmodel.ViewModelFactory
import java.util.Comparator
import java.util.stream.Collectors
import javax.inject.Inject
import android.text.Editable
import android.text.TextWatcher
import com.google.android.gms.maps.GoogleMap

/**
 * Created by Sai Kiran on 03-07-2021.
 */
class ListFragment : BaseFragment(), onCityInterface {


    @set:Inject
    var viewModelFactory: ViewModelFactory? = null
    private lateinit var adapter: RecyclerAdapter
    private lateinit var binding: ScreenListBinding
    private var onItemClickListener: OnItemClickListener? = null


    private var viewModel: ListViewModel? = null

    override fun layoutRes(): Int {
        return R.layout.screen_list
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java)
        viewModel?.getCitiesResponse()?.observe(this, Observer { onCitiesResponse(it!!) })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.screen_list, container, false);
        binding.listViewModel = viewModel

       val layoutRManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutRManager;

        adapter = RecyclerAdapter(mutableListOf(), this)
//
//        val linearLayoutManager = LinearLayoutManager(activity)
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        binding.recyclerView.layoutManager = linearLayoutManager;
        binding.recyclerView.adapter = adapter


        createSearchView();
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel?.fetchCities(context!!)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun onCitiesResponse(citiesResponse: CitiesResponse) {

        val compareByName = Comparator.comparing(CitiesResponseItem::name)
                .thenComparing(CitiesResponseItem::country);

        val sortedCities = citiesResponse.stream()
                .sorted(compareByName)
                .collect(Collectors.toList<CitiesResponseItem>())

        adapter = RecyclerAdapter(sortedCities, this);
        binding.recyclerView.adapter = adapter
        binding.loadingView.visibility = View.GONE
    }

    private fun createSearchView() {
        binding.searchText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(cs: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
                adapter.filter.filter(cs)
            }

            override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int,
                                           arg3: Int) {
            }

            override fun afterTextChanged(arg0: Editable) {
            }
        })
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onClick(citiesResponseItem: CitiesResponseItem)
    }

    override fun onCityClick(citiesResponseItem: CitiesResponseItem) {
        if (onItemClickListener != null) {
            onItemClickListener?.onClick(citiesResponseItem)
        }
    }

}