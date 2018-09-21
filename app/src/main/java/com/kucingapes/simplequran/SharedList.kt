package com.kucingapes.simplequran

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kucingapes.simplequran.model.mLSurat
import com.kucingapes.simplequran.model.mNamaSurat
import com.kucingapes.simplequran.model.mSurat

class SharedList {

    val gson = Gson()

    fun getListQuran(context: Context, data: String): List<mSurat> {
        val sharedPref = context.getSharedPreferences("quran_data", Context.MODE_PRIVATE)
        val type = object : TypeToken<List<mSurat>>() {}.type
        val json = sharedPref.getString(data, null)
        return gson.fromJson(json, type)
    }

    fun getListTitle(context: Context): List<mNamaSurat> {
        val sharedPref = context.getSharedPreferences("quran_data", Context.MODE_PRIVATE)
        val type = object : TypeToken<List<mNamaSurat>>() {}.type
        val json = sharedPref.getString("title", null)
        return gson.fromJson(json, type)
    }

    fun getListLiteration(context: Context): List<mLSurat> {
        val sharedPref = context.getSharedPreferences("quran_data", Context.MODE_PRIVATE)
        val type = object : TypeToken<List<mLSurat>>() {}.type
        val json = sharedPref.getString("literation", null)
        return gson.fromJson(json, type)
    }
}