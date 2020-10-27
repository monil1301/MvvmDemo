package com.shah.mvvmdemo.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CourseDetails(
    val imageUrl: String,
    val title: String,
    val teacher: TeacherDetails,
    val description: String,
    val rating: Float,
    val totalRatings: String,
    val price: String,
    val cancelledPrice: String,
    val isBestseller: Boolean,
    val timeWatched: Float,
    val updatedOn: String,
    val language: String,
    val toLearnList: ArrayList<String>,
    val detailedDescription: Int,
    val requirementsList: ArrayList<String>,
    val lectures: Int,
    val time: String,
    val curriculum: ArrayList<CurriculumDetails>
) : Parcelable