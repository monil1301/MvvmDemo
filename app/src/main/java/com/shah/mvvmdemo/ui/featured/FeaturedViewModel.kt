package com.shah.mvvmdemo.ui.featured

import com.shah.mvvmdemo.data.repository.UserRepository
import com.shah.mvvmdemo.data.response.CourseCategory
import com.shah.mvvmdemo.data.response.CourseDetails
import com.shah.mvvmdemo.ui.base.BaseViewModel

class FeaturedViewModel(private val userRepository: UserRepository) :
    BaseViewModel(userRepository) {

    private val title = "2020 Complete Python Bootcamp: From Zero to Hero in Python"
    private val imageUrl =
        "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ89CCZmY4pB5cbLMhVIb168rrKgCOCGvUvIA&usqp=CAU"
    private val description =
        "Learn python like a Professional! Start from the basics and go all the way to create your own applicatons and games!"

    private val courseDetailList: ArrayList<CourseDetails> = ArrayList()

    private fun addCourseDetailList() {
        courseDetailList.add(
            CourseDetails(
                imageUrl,
                title,
                "Jose Portilla",
                description,
                4.6F,
                "34,546",
                "500.00",
                "8500.00",
                true,
                21.5F,
                "06/2020",
                "CC, English, French, German, 4 more"
            )
        )
        courseDetailList.add(
            CourseDetails(
                imageUrl,
                title,
                "Jose Portilla",
                description,
                4.6F,
                "34,546",
                "500.00",
                "8500.00",
                false,
                21.5F,
                "06/2020",
                "CC, English, French, German, 4 more"
            )
        )
        courseDetailList.add(
            CourseDetails(
                imageUrl,
                title,
                "Jose Portilla",
                description,
                4.6F,
                "34,546",
                "500.00",
                "8500.00",
                true,
                21.5F,
                "06/2020",
                "CC, English, French, German, 4 more"
            )
        )
        courseDetailList.add(
            CourseDetails(
                imageUrl,
                title,
                "Jose Portilla",
                description,
                4.6F,
                "34,546",
                "500.00",
                "8500.00",
                false,
                21.5F,
                "06/2020",
                "CC, English, French, German, 4 more"
            )
        )
        courseDetailList.add(
            CourseDetails(
                imageUrl,
                title,
                "Jose Portilla",
                description,
                4.6F,
                "34,546",
                "500.00",
                "8500.00",
                false,
                21.5F,
                "06/2020",
                "CC, English, French, German, 4 more"
            )
        )
    }

    fun getCategoryList(): ArrayList<CourseCategory?> {
        val categoryList: ArrayList<CourseCategory?> = ArrayList()
        addCourseDetailList()
        categoryList.add(null)
        categoryList.add(CourseCategory("Programming", courseDetailList))
        categoryList.add(CourseCategory("UI UX", courseDetailList))
        categoryList.add(CourseCategory("Drawing", courseDetailList))
        categoryList.add(null)
        categoryList.add(CourseCategory("PhotoGraphy", courseDetailList))
        categoryList.add(CourseCategory("Programming", courseDetailList))
        return categoryList
    }

}