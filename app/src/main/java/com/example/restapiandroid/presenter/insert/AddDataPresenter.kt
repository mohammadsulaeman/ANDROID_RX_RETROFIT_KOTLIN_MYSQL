package com.example.restapiandroid.presenter.insert

import android.annotation.SuppressLint
import com.example.restapiandroid.network.api.Log
import com.example.restapiandroid.network.api.Restfactory
import com.example.restapiandroid.network.api.service.RestService
import com.example.restapiandroid.network.model.BaseResponse
import com.example.restapiandroid.network.model.TambahUsersRequestJson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class AddDataPresenter(val view: AddDataView) : AddDataInterface {
    private var TAG = "MainActivity"

    @SuppressLint("CheckResult")
    override fun getTambahData(
        nama: String,
        alamat: String,
        phone: String,
        ttl: String,
        pendidikan: String,
        email: String
    ) {
        getObserverAdd(nama, alamat, email,  phone, pendidikan, ttl).subscribeWith(getObserverResponse())
    }

    fun getObserverAdd(nama: String, alamat: String,  email: String,  phone: String, pendidikan: String, ttl: String ) : Observable<BaseResponse> {
        var requestJson : TambahUsersRequestJson = TambahUsersRequestJson(nama,  alamat, phone, ttl, pendidikan, email)
        return Restfactory.createService(RestService::class.java, nama, email)
            .insertUsers(requestJson)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getObserverResponse() = object : DisposableObserver<BaseResponse>(){
        override fun onNext(baseResponse: BaseResponse) {
            if (baseResponse.status.equals("success")){
                view.onSuccess(baseResponse.message)
                view.onHideLoading()
            }
        }

        override fun onError(e: Throwable) {
            view.onError(e.message.toString())
            view.showLoading()
        }

        override fun onComplete() {
            Log.d(TAG, "Data Berhasil DI Simpan")
            view.onHideLoading()
        }

    }
}