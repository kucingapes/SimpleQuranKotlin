package com.kucingapes.simplequran.restapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Client {

    private val BASEURL = "https://qurandb.herokuapp.com/"
    private var retrofit: Retrofit? = null

    fun getRetrofit(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit
    }
}