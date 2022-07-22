package com.example.restapiandroid.network.api.service

import com.example.restapiandroid.network.model.*
import io.reactivex.Observable
import retrofit2.http.*

interface RestService {
    @GET("biodata/allusers")
    fun getDataUsers() : Observable<GetDataUsersResponseJson>

    @POST("biodata/insert_biodata")
    fun insertUsers(@Body requestJson: TambahUsersRequestJson) : Observable<BaseResponse>

    @POST("biodata/update_biodata/{id}")
    fun updateUsers(@Body requestJson: UpdateUsersRequestJson, @Path("id") id : String) : Observable<BaseResponse>

    @DELETE("biodata/delete_biodata/{id}")
    fun deleteUsers(@Path("id") id: String) : Observable<BaseResponse>
}