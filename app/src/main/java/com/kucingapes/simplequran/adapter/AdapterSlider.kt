package com.kucingapes.simplequran.adapter

import android.content.Context
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kucingapes.simplequran.R
import com.kucingapes.simplequran.SharedList
import com.kucingapes.simplequran.model.mNamaSurat
import com.kucingapes.simplequran.model.mSurat
import kotlinx.android.synthetic.main.item_slider.view.*
import kotlinx.android.synthetic.main.toolbar.view.*

class AdapterSlider(var context: Context,
                    var listArabic: List<mSurat>,
                    var listIndo: List<mSurat>,
                    var listTitle: List<mNamaSurat>): RecyclerView.Adapter<AdapterSlider.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_slider, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return listTitle.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val titleSurat = listTitle[position]

        val sharedList = SharedList()
        val lArabic = listArabic[position].ayat
        val lIndo = listIndo[position].ayat
        val lLiteration = sharedList.getListLiteration(context)[position].literationAyat

        val layoutManager = LinearLayoutManager(context)
        val divider = DividerItemDecoration(context, layoutManager.orientation)
        val chilAdapter = ChildAdapterSlider(context, lArabic, lIndo, lLiteration!!, position)

        holder.title.text = titleSurat.chapterName
        holder.ayatList.layoutManager = layoutManager
        holder.ayatList.addItemDecoration(divider)
        holder.ayatList.adapter = chilAdapter
    }


    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val title = itemView!!.toolbar_title
        val ayatList = itemView!!.list_ayat
    }
}