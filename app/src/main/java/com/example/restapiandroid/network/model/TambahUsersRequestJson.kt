package com.example.restapiandroid.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TambahUsersRequestJson(
    @SerializedName("nama")
    @Expose
    val nama : String,


    @SerializedName("alamat")
    @Expose
    val alamat : String,

    @SerializedName("phone")
    @Expose
    val phone : String,

    @SerializedName("ttl")
    @Expose
    val ttl : String,

    @SerializedName("pendidikan")
    @Expose
    val pendidikan : String,

    @SerializedName("email")
    @Expose
    val email : String
)