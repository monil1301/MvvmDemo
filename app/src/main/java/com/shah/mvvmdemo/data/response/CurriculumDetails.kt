package com.shah.mvvmdemo.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurriculumDetails (val title: String, val lectures: List<LectureDetails>, var isExpanded: Boolean) :
    Parcelable