package com.shah.mvvmdemo.ui.home.featured

import com.shah.mvvmdemo.R
import com.shah.mvvmdemo.data.repository.UserRepository
import com.shah.mvvmdemo.data.response.*
import com.shah.mvvmdemo.ui.base.BaseViewModel

class FeaturedViewModel(private val userRepository: UserRepository) :
    BaseViewModel(userRepository) {

    private val title = "2020 Complete Python Bootcamp: From Zero to Hero in Python"
    private val imageUrl =
        "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ89CCZmY4pB5cbLMhVIb168rrKgCOCGvUvIA&usqp=CAU"
    private val description =
        "Learn python like a Professional! Start from the basics and go all the way to create your own applicatons and games!"
    private val toLearnList = arrayListOf(
        "Learn to use Python professionally, learning both Python 2 and Python 3!",
        "Create games with Python, like Tic Tac Toe and Blackjack!",
        "Learn advanced Python features, like the collections module and how to work with timestamps!",
        "Learn to use Object Oriented Programming with classes!",
        "Understand complex topics, like decorators.",
        "Understand how to use both the Jupyter Notebook and create .py files",
        "Get an understanding of how to create GUIs in the Jupyter Notebook system!",
        "Build a complete understanding of Python from the ground up!"
    )
    private val detailedDescription = (R.string.detailed_description)
    private val requirementsList = arrayListOf("Access to a computer with an internet connection.")

    private val lecture1 = LectureDetails("Auto-Welcome Message","Article", "00:44 mins", 1)
    private val lecture2 = LectureDetails("Course Introduction","Video", "06:39 mins", 2)
    private val lecture3 = LectureDetails("Course Curriculum Overview","Video", "04:00 mins", 3)
    private val lecture4 = LectureDetails("Why Python?","Video", "05:18 mins", 4)
    private val lecture5 = LectureDetails("Course FAQs","Article", "02:33 mins",5)
    private val lecturesList = listOf(lecture1,lecture2,lecture3,lecture4,lecture5)

    private val curriculum1 = CurriculumDetails("Course Overview", lecturesList,true)
    private val curriculum2 = CurriculumDetails("Python Setup", lecturesList,false)
    private val curriculum3 = CurriculumDetails("Python Object and Data Structure Basics", lecturesList,false)
    private val curriculum4 = CurriculumDetails("Python Comparision Operators", lecturesList,false)
    private val curriculum5 = CurriculumDetails("Python Statements", lecturesList,false)
    private val curriculum6 = CurriculumDetails("Methods and Function", lecturesList,false)
    private val curriculum7 = CurriculumDetails("Conclusion", lecturesList,false)

    private val teacher = TeacherDetails("Jose Portilla", "https://png.pngtree.com/png-vector/20190710/ourmid/pngtree-user-vector-avatar-png-image_1541962.jpg", 4.6F, 30, "1,924,365")

    private val curriculumList = arrayListOf(curriculum1,curriculum2,curriculum3,curriculum4,curriculum5,curriculum6,curriculum7)

    private val courseDetailList: ArrayList<CourseDetails> = ArrayList()

    private fun addCourseDetailList() {
        courseDetailList.add(
            CourseDetails(
                imageUrl,
                title,
                teacher,
                description,
                4.6F,
                "34,546",
                "500.00",
                "8500.00",
                true,
                21.5F,
                "06/2020",
                "CC, English, French, German, 4 more",
                toLearnList,
                detailedDescription,
                requirementsList,
                152,
                "21h 33m",
                curriculumList
            )
        )
        courseDetailList.add(
            CourseDetails(
                imageUrl,
                title,
                teacher,
                description,
                4.6F,
                "34,546",
                "500.00",
                "8500.00",
                false,
                21.5F,
                "06/2020",
                "CC, English, French, German, 4 more",
                toLearnList,
                detailedDescription,
                requirementsList,
                152,
                "21h 33m",
                curriculumList
            )
        )
        courseDetailList.add(
            CourseDetails(
                imageUrl,
                title,
                teacher,
                description,
                4.6F,
                "34,546",
                "500.00",
                "8500.00",
                true,
                21.5F,
                "06/2020",
                "CC, English, French, German, 4 more",
                toLearnList,
                detailedDescription,
                requirementsList,
                152,
                "21h 33m",
                curriculumList
            )
        )
        courseDetailList.add(
            CourseDetails(
                imageUrl,
                title,
                teacher,
                description,
                4.6F,
                "34,546",
                "500.00",
                "8500.00",
                false,
                21.5F,
                "06/2020",
                "CC, English, French, German, 4 more",
                toLearnList,
                detailedDescription,
                requirementsList,
                152,
                "21h 33m",
                curriculumList
            )
        )
        courseDetailList.add(
            CourseDetails(
                imageUrl,
                title,
                teacher,
                description,
                4.6F,
                "34,546",
                "500.00",
                "8500.00",
                false,
                21.5F,
                "06/2020",
                "CC, English, French, German, 4 more",
                toLearnList,
                detailedDescription,
                requirementsList,
                152,
                "21h 33m",
                curriculumList
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