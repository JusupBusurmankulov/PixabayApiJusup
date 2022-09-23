package com.example.pixabayapijusup.Retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {
    @GET("api/?key=30100651-7baa6772e716715a5e1798776")
    fun getImages(
        @Query("q") q: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10
    ): Call<PixabayModel>

}