package com.example.restapiandroid.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetDataUsersResponseJson(
    @SerializedName("biodata")
    @Expose
    val biodata : List<Users>,

    @SerializedName("status")
    @Expose
    val status : String,

    @SerializedName("message")
    @Expose
    val message : String
)