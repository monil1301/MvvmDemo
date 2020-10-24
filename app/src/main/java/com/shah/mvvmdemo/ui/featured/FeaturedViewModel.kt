package com.shah.mvvmdemo.ui.featured

import com.shah.mvvmdemo.data.repository.UserRepository
import com.shah.mvvmdemo.data.response.CourseCategory
import com.shah.mvvmdemo.data.response.CourseDetails
import com.shah.mvvmdemo.ui.base.BaseViewModel

class FeaturedViewModel(private val userRepository: UserRepository): BaseViewModel(userRepository) {

    private val title = "2020 Complete Python BootCamp: From Zero to Hero"
    private val imageUrl =
        "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ89CCZmY4pB5cbLMhVIb168rrKgCOCGvUvIA&usqp=CAU"

    private val courseDetailList: ArrayList<CourseDetails> = ArrayList()

    private fun addCourseDetailList() {
        courseDetailList.add(
            CourseDetails(imageUrl, title, "Jose Portilla", 4.6F,"34,546", "500.00","8500.00", true)
        )
        courseDetailList.add(
            CourseDetails(imageUrl, title, "Jose Portilla", 4.6F, "34,546","500.00","8500.00", false)
        )
        courseDetailList.add(
            CourseDetails(imageUrl, title, "Jose Portilla", 4.6F, "34,546","500.00","8500.00", true)
        )
        courseDetailList.add(
            CourseDetails(imageUrl, title, "Jose Portilla", 4.6F, "34,546","500.00","8500.00", false)
        )
        courseDetailList.add(
            CourseDetails(imageUrl, title, "Jose Portilla", 4.6F, "34,546","500.00","8500.00", false)
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