package com.shah.mvvmdemo.ui.course

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.shah.mvvmdemo.R
import com.shah.mvvmdemo.data.response.CourseDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_course_detail.*

class CourseDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_detail)

        val courseDetails = intent.getParcelableExtra<CourseDetails>("course") ?: return

        course_title.text = courseDetails.title
        course_description.text = courseDetails.description

        val highlights: ArrayList<String> = ArrayList()

        highlights.add(courseDetails.rating.toString())
        highlights.add("${courseDetails.totalRatings} Enrolled")
        highlights.add("${courseDetails.timeWatched} total hours")
        highlights.add("Created by ${courseDetails.teacher}")
        highlights.add("Updated ${courseDetails.updatedOn}")
        highlights.add(courseDetails.language)

        course_highlights_recycler.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        course_highlights_recycler.adapter = CourseHighlightAdapter(highlights)

        Picasso.get()
            .load(courseDetails.imageUrl)
            .into(course_image_view)

        course_price.text = course_price.text.toString() + courseDetails.price
        course_hours_on_demand.text = getString(R.string.hours_on_demand_video, courseDetails.timeWatched.toString())
    }
}