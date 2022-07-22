package com.example.restapiandroid.presenter.editdel

import android.annotation.SuppressLint
import com.example.restapiandroid.network.api.Log
import com.example.restapiandroid.network.api.Restfactory
import com.example.restapiandroid.network.api.service.RestService
import com.example.restapiandroid.network.model.BaseResponse
import com.example.restapiandroid.network.model.DeleteDataRequestJson
import com.example.restapiandroid.network.model.UpdateUsersRequestJson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class EditDeleteDataUsersPresenter(var view: EditDeleteView) : EditDeleteInterface {

    private var TAG = "MainActivity"
    @SuppressLint("CheckResult")
    override fun getEditData(
        id: String,
        nama: String,
        alamat: String,
        phone: String,
        ttl: String,
        pendidikan: String,
        email: String
    ) {
        getObserverEdit(id, nama, alamat, phone, ttl, pendidikan, email).subscribeWith(getDisposeble())
    }

    @SuppressLint("CheckResult")
    override fun getDeleteData(id: String) {
        getObserverDelete(id).subscribeWith(getDisposebleDelete())
    }

    fun getObserverEdit( id: String,
                         nama: String,
                         alamat: String,
                         phone: String,
                         ttl: String,
                         pendidikan: String,
                         email: String) : Observable<BaseResponse>
    {
        var requestjson : UpdateUsersRequestJson = UpdateUsersRequestJson(id, nama, alamat, phone, ttl, pendidikan, email)
        Log.d(TAG, "RequestJsonUpdate = $requestjson")
        return Restfactory.createService(RestService::class.java,id,nama)
            .updateUsers(requestjson,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getObserverDelete( id: String) : Observable<BaseResponse>
    {
       var request : DeleteDataRequestJson = DeleteDataRequestJson(id)
        Log.d(TAG, "RequestJson Delete = $request")
        return Restfactory.createService(RestService::class.java,id,id)
            .deleteUsers(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getDisposeble() =  object : DisposableObserver<BaseResponse>()
    {
        override fun onNext(baseResponse: BaseResponse) {
            if (baseResponse.status.equals("success")){
                view.onSuccess(baseResponse.message)
                view.hideLoading()
            }else{
                view.onError(baseResponse.message)
                view.showLoading()
            }
        }

        override fun onError(e: Throwable) {
            view.onError(e.message.toString())
            view.showLoading()
        }

        override fun onComplete() {
           Log.d(TAG, "OnComplete : Data Berhasil di update ")
        }

    }

    fun getDisposebleDelete() =  object : DisposableObserver<BaseResponse>()
    {
        override fun onNext(baseResponse: BaseResponse) {
            if (baseResponse.status.equals("success")){
                view.onSuccess(baseResponse.message)
                view.hideLoading()
            }else{
                view.onError(baseResponse.message)
                view.showLoading()
            }
        }

        override fun onError(e: Throwable) {
            view.onError(e.message.toString())
            view.showLoading()
        }

        override fun onComplete() {
            Log.d(TAG, "OnComplete : Data Berhasil di Delete ")
        }

    }
}