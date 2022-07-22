package com.example.restapiandroid.presenter.read

import com.example.restapiandroid.network.model.GetDataUsersResponseJson

interface GetDataViewInterface {
    fun showLoading()
    fun hideLoading()
    fun onSuccess(message : String)
    fun onError(message: String)
    fun onGetDataUsersAll(request : GetDataUsersResponseJson)
}