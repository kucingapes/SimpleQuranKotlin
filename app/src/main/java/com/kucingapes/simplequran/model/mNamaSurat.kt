package com.kucingapes.simplequran.model

import com.google.gson.annotations.SerializedName

class mNamaSurat(@field:SerializedName("ChapterName") var chapterName: String?,
                 @field:SerializedName("ChapterID") var chapterId: Int?,
                 @field:SerializedName("Translate") var translate: String?) {
}