package com.example.restapiandroid.presenter.editdel

interface EditDeleteInterface {
    // update
    fun getEditData(id : String, nama : String, alamat : String,  phone: String,ttl: String,pendidikan: String, email: String)

    // delete
    fun getDeleteData(id: String)
}