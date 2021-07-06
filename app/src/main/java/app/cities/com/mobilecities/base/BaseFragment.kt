package app.cities.com.mobilecities.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment

/**
 * Created by Ramana on 16-09-2020.
 */
abstract class BaseFragment : DaggerFragment() {
    private var activity: AppCompatActivity? = null

    @LayoutRes
    protected abstract fun layoutRes(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutRes(), container, false)
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activity = context as AppCompatActivity?
    }

    override fun onDetach() {
        super.onDetach()
        activity = null
    }

    fun getBaseActivity(): AppCompatActivity? {
        return activity
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}