package com.my.cvapp.network

import com.my.cvapp.model.CVDetail
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    //Implement all api call methods here.

    /*
    * To get albums information.
    * */
    //14u7d2
    /*
    * Multiple user data
    * */
    @GET("bins/1ft1va")
    fun getCVDetailInfo(): Call<List<CVDetail>>
}
