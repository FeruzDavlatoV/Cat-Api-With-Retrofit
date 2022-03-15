package com.example.sendimgefilewithretrofit.networking

import com.example.sendimgefilewithretrofit.networking.model.Breed
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("images/search?")
    fun getActivity(@Query("query") query:String,
                    @Query ("page") page:Int,
                    @Query("limit") limit:Int)
                    :Call<Breed>
}