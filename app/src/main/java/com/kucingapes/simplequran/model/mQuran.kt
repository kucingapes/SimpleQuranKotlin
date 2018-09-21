package com.kucingapes.simplequran.model

import com.google.gson.annotations.SerializedName

class mQuran(@field:SerializedName("arabic") var arabic: List<mSurat>?,
             @field:SerializedName("id") var indo: List<mSurat>?)