package com.kucingapes.simplequran.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kucingapes.simplequran.R
import com.kucingapes.simplequran.activity.SuratSlider
import com.kucingapes.simplequran.model.mLSurat
import com.kucingapes.simplequran.model.mNamaSurat
import com.kucingapes.simplequran.model.mSurat
import kotlinx.android.synthetic.main.item_surat.view.*

class AdapterSurat(var context: Context,
                   var title: List<mNamaSurat>,
                   var arabic: List<mSurat>,
                   var listLiteration: List<mLSurat>) : RecyclerView.Adapter<AdapterSurat.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_surat, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return title.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val titleText = title[position]
        val arabicText = arabic[position]
        val surat = listLiteration[position]

        var type = surat.relevationType
        if (type.equals("Meccan")) {
            type = "Makkiah"
        } else if (type.equals("Medinan")) {
            type = "Madaniyah"
        }

        holder.itemView.number.text = titleText.chapterId.toString()
        holder.itemView.title_en.text = titleText.chapterName
        holder.itemView.title_trans.text = titleText.translate
        holder.itemView.ayat_size.text = arabicText.ayat.size.toString()+" ayat"
        holder.itemView.type.text = type
        holder.itemView.arabic_title.text = arabicText.name

        holder.itemView.setOnClickListener {
            val intent = Intent(context, SuratSlider::class.java)
            intent.putExtra("position", position)
            context.startActivity(intent)
        }
    }

    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView)

}