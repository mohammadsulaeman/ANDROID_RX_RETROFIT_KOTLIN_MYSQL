package com.example.restapiandroid.presenter.insert

import com.example.restapiandroid.network.model.Users

interface AddDataInterface {
    public fun getTambahData(nama : String, alamat : String,  phone: String,ttl: String,pendidikan: String, email: String)
}