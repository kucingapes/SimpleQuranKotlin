package com.kucingapes.simplequran

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.kucingapes.simplequran.model.*
import com.kucingapes.simplequran.restapi.Client
import com.kucingapes.simplequran.restapi.IClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var sharedPref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quranClient = Client.getRetrofit()!!.create(IClient::class.java)
        val getQuranDB = quranClient.getQuranDB()

        sharedPref = getSharedPreferences("quran_data", Context.MODE_PRIVATE)

        if (sharedPref!!.contains("quran") &&
                sharedPref!!.contains("indo") &&
                sharedPref!!.contains("title") &&
                sharedPref!!.contains("literation")) {

            Toast.makeText(baseContext, "data oke", Toast.LENGTH_SHORT).show()
            startActivity(Intent(baseContext, Surat::class.java))
            finish()

        } else {
            Toast.makeText(baseContext, "mengambil data", Toast.LENGTH_SHORT).show()
            getDataBase(getQuranDB, quranClient)
        }

    }

    private fun getDataBase(getQuranDB: Call<mQuran>, quranClient: IClient) {
        getQuranDB.enqueue(object : Callback<mQuran> {
            override fun onFailure(call: Call<mQuran>, t: Throwable) {
                Log.d("bangke", t.message)
                Toast.makeText(baseContext, "gagal", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<mQuran>, response: Response<mQuran>) {
                val quran = response.body()!!.arabic
                val indo = response.body()!!.indo

                val gson = Gson()
                val jsonQuran = gson.toJson(quran)
                val jsonIndo = gson.toJson(indo)
                with(sharedPref!!.edit()) {
                    putString("quran", jsonQuran)
                    putString("indo", jsonIndo)
                    apply()
                    Toast.makeText(baseContext, "sukses", Toast.LENGTH_SHORT).show()

                    //loadingbar.visibility = View.GONE
                    startActivity(Intent(baseContext, Surat::class.java))
                    finish()
                }
            }
        })

        val getQuranTitle = quranClient.getQuranTitle()
        getQuranTitle.enqueue(object : Callback<mNSuratList> {
            override fun onFailure(call: Call<mNSuratList>, t: Throwable) {
                Log.d("suee", t.message)
                Toast.makeText(baseContext, "gagal", Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<mNSuratList>, response: Response<mNSuratList>) {
                val title = response.body()!!.namaSuratList

                val gson = Gson()
                val jsonTitle = gson.toJson(title)
                with(sharedPref!!.edit()) {
                    putString("title", jsonTitle)
                    apply()

                    Toast.makeText(baseContext, "title sukses", Toast.LENGTH_SHORT).show()
                }
            }
        })

        val getQuranLiteration = quranClient.getQuranLiteration()
        getQuranLiteration.enqueue(object : Callback<mLiteration> {
            override fun onFailure(call: Call<mLiteration>, t: Throwable) {
                //
            }

            override fun onResponse(call: Call<mLiteration>, response: Response<mLiteration>) {
                val literation = response.body()!!.literation

                /*loadingbar.visibility = View.GONE
                Toast.makeText(baseContext, literation.size.toString(), Toast.LENGTH_SHORT).show()*/

                val gson = Gson()
                val jsonLiteration = gson.toJson(literation)
                with(sharedPref!!.edit()) {
                    putString("literation", jsonLiteration)
                    apply()
                    Toast.makeText(baseContext, "sukses", Toast.LENGTH_SHORT).show()

                }

            }

        })
    }
}
