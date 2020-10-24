package com.shah.mvvmdemo.data.response

data class CourseDetails(
    val imageUrl: String,
    val title: String,
    val teacher: String,
    val rating: Float,
    val totalRatings: String,
    val price: String,
    val cancelledPrice: String,
    val isBestseller: Boolean
)