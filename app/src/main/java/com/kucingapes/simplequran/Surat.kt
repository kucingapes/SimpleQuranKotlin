package com.kucingapes.simplequran

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.kucingapes.simplequran.adapter.AdapterSurat
import kotlinx.android.synthetic.main.activity_surat.*

class Surat : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surat)

        val sharedList = SharedList()
        val listQuran = sharedList.getListQuran(this, "quran")
        val listTitle = sharedList.getListTitle(this)
        val listLiteration = sharedList.getListLiteration(this)

        val layoutManager = LinearLayoutManager(this)
        val divider = DividerItemDecoration(this, layoutManager.orientation)
        val adapterSurat = AdapterSurat(this, listTitle, listQuran, listLiteration)
        recycler_surat.layoutManager = layoutManager
        recycler_surat.addItemDecoration(divider)
        recycler_surat.adapter = adapterSurat


    }
}
