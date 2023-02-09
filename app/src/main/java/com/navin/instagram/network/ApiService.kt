package com.navin.radiojavan.api

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    var retrofit = Retrofit.Builder()
        .baseUrl("https://mobilemasters.ir/apps/instagram/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}