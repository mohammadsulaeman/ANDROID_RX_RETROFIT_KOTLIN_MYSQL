package com.example.restapiandroid.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DeleteDataRequestJson(
    @SerializedName("id")
    @Expose
    val id : String
)