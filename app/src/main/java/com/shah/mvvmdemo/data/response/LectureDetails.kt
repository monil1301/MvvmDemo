package com.shah.mvvmdemo.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LectureDetails (val title: String, val type: String, val length: String, val number: Int) : Parcelable