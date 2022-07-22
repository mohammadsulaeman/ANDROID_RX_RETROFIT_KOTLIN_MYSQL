package com.example.restapiandroid.presenter.insert

interface AddDataView {
    fun onSuccess(message : String)
    fun onError(message: String)
    fun showLoading()
    fun onHideLoading()
}