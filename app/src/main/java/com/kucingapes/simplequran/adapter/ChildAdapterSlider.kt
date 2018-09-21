package com.kucingapes.simplequran.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kucingapes.simplequran.R
import com.kucingapes.simplequran.model.*
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.item_ayat.view.*

class ChildAdapterSlider(var context: Context,
                         var listArabic: List<mAyat>,
                         var listIndo: List<mAyat>,
                         var listLiteration: List<mLAyat>,
                         var numberAyat: Int?): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_HEADER = 0
    private val TYPE_ITEM = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_HEADER) {
            val view: View = LayoutInflater.from(context).inflate(R.layout.header, parent, false)
            return Header(view)
        } else if (viewType == TYPE_ITEM) {
            val view: View = LayoutInflater.from(context).inflate(R.layout.item_ayat, parent, false)
            return Item(view)
        } else
            return null!!
    }

    override fun getItemCount(): Int {
        return listArabic.size+1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_HEADER else TYPE_ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is Header) {
            holder.basmalahHeader.text = "بِسْمِ ٱللَّهِ ٱلرَّحْمَٰنِ ٱلرَّحِيمِ"

            holder.setIsRecyclable(false)
            if (numberAyat == 0 || numberAyat == 8) {
                holder.layoutHeader.visibility = View.GONE
            }
        } else if (holder is Item) {
            val arabic = listArabic[position-1]
            val indo = listIndo[position-1]
            val literation = listLiteration[position-1]

            holder.setIsRecyclable(false)
            holder.ayatNumber.text = position.toString()
            holder.ayatArabic.text = arabic.text
            holder.ayatIndo.text = indo.text
            holder.ayatLitearion.text = Html.fromHtml("<i>"+literation.tText+"</i>")
        }
    }

    class Item(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val ayatArabic = itemView!!.text_arabic
        val ayatIndo = itemView!!.text_indo
        val ayatLitearion = itemView!!.text_literation
        val ayatNumber = itemView!!.number_ayat
    }

    class Header(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val basmalahHeader = itemView!!.basmallah
        val layoutHeader = itemView!!.header_layout
    }

}