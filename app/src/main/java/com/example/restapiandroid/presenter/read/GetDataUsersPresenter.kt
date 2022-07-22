package com.example.restapiandroid.presenter.read

import android.annotation.SuppressLint
import com.example.restapiandroid.network.api.Log
import com.example.restapiandroid.network.api.Restfactory
import com.example.restapiandroid.network.api.service.RestService
import com.example.restapiandroid.network.model.GetDataUsersResponseJson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class GetDataUsersPresenter(val viewInterface: GetDataViewInterface)  : GetViewInterface{

    private var TAG = "MainActivity"

    @SuppressLint("CheckResult")
    override fun getUsers() {
        getObserverUsers().subscribeWith(getObserverDispose())
    }

    fun getObserverUsers() : Observable<GetDataUsersResponseJson> {
        return Restfactory.createService(RestService::class.java, "admin", "123456")
            .getDataUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getObserverDispose() = object : DisposableObserver<GetDataUsersResponseJson>(){
        override fun onNext(responsejson: GetDataUsersResponseJson) {
            Log.d(TAG, "OnNext : ${responsejson.biodata}")
            viewInterface.onGetDataUsersAll(responsejson)
            viewInterface.onSuccess(responsejson.message)
            viewInterface.hideLoading()
        }

        override fun onError(e: Throwable) {
            Log.d(TAG, "OnError = ${e.message}")
            viewInterface.showLoading()
            viewInterface.onError(e.message.toString())
        }

        override fun onComplete() {
            Log.d(TAG, "OnComplete")
            viewInterface.hideLoading()
        }

    }
}