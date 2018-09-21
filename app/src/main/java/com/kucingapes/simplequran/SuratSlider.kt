package com.kucingapes.simplequran

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import com.kucingapes.simplequran.adapter.AdapterSlider
import kotlinx.android.synthetic.main.activity_surat_slider.*

class SuratSlider : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surat_slider)

        val position = intent.getIntExtra("position", 0)

        val sharedList = SharedList()
        val listQuran = sharedList.getListQuran(this, "quran")
        val listIndo = sharedList.getListQuran(this, "indo")
        val listTitle = sharedList.getListTitle(this)
        val listLiteration = sharedList.getListLiteration(this)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        val snapHelper = PagerSnapHelper()
        val adapter = AdapterSlider(this, listQuran, listIndo, listTitle)

        snapHelper.attachToRecyclerView(slider_surat)
        slider_surat.layoutManager = layoutManager
        slider_surat.adapter = adapter
        slider_surat.scrollToPosition(position)
    }
}
