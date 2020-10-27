package com.shah.mvvmdemo.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeacherDetails (val name: String, val imageUrl: String, val rating: Float, val courses: Int, val students: String) :
    Parcelable