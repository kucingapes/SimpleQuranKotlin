package com.kucingapes.simplequran.restapi

import com.kucingapes.simplequran.model.mLiteration
import com.kucingapes.simplequran.model.mQuran
import com.kucingapes.simplequran.model.mNSuratList
import retrofit2.Call
import retrofit2.http.GET

interface IClient {
    @GET("surah") fun getQuranDB(): Call<mQuran>
    @GET("title") fun getQuranTitle(): Call<mNSuratList>
    @GET("literation") fun getQuranLiteration(): Call<mLiteration>
}