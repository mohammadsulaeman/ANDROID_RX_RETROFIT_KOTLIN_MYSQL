package com.example.restapiandroid.presenter.editdel

interface EditDeleteView {
    fun showLoading()
    fun hideLoading()
    fun onSuccess(message : String)
    fun onError(message: String)
}