package app.cities.com.mobilecities.viewmodel

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Sai Kiran on 03-07-2021.
 */
abstract class DisposingViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
    override fun onCleared() {
        compositeDisposable.clear()
    }
}