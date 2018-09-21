package com.kucingapes.simplequran.model

import com.google.gson.annotations.SerializedName

class mLSurat(@field:SerializedName("englishName") var englishName: String?,
              @field:SerializedName("revelationType") var relevationType: String?,
              @field:SerializedName("ayahs") var literationAyat: List<mLAyat>?)