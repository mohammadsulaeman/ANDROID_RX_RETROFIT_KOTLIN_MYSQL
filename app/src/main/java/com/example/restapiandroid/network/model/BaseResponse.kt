package com.example.restapiandroid.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("status")
    @Expose
    val status : String,

    @SerializedName("message")
    @Expose
    val message : String
)