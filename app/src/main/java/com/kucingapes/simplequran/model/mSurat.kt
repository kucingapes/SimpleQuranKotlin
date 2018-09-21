package com.kucingapes.simplequran.model

import com.google.gson.annotations.SerializedName

class mSurat(@field:SerializedName("aya") var ayat: List<mAyat>,
             @field:SerializedName("index") var index: Int?,
             @field:SerializedName("name") var name: String?)