package com.shah.mvvmdemo.ui.course

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.WindowManager
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.material.internal.FlowLayout
import com.shah.mvvmdemo.R
import com.shah.mvvmdemo.data.response.CourseDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_course_detail.*

class CourseDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_course_detail)

        setSupportActionBar(findViewById(R.id.course_detail_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val courseDetails = intent.getParcelableExtra<CourseDetails>("course") ?: return

        course_title.text = courseDetails.title
        course_description.text = courseDetails.description

        val highlights: ArrayList<String> = ArrayList()

        highlights.add(courseDetails.rating.toString())
        highlights.add("${courseDetails.totalRatings} Enrolled")
        highlights.add("${courseDetails.timeWatched} total hours")
        highlights.add(getString(R.string.teacher,courseDetails.teacher.name))
        highlights.add("Updated ${courseDetails.updatedOn}")
        highlights.add(courseDetails.language)

        course_highlights_recycler.layoutManager = FlexboxLayoutManager(this)
        course_highlights_recycler.adapter = CourseHighlightAdapter(highlights)

        Picasso.get()
            .load(courseDetails.imageUrl)
            .into(course_image_view)

        course_price.text = getString(R.string.Rs, courseDetails.price)
        course_hours_on_demand.text = getString(R.string.hours_on_demand_video, courseDetails.timeWatched.toString())
        course_what_wil_i_learn_recycler.adapter = CourseWhatYouLearnAdapter(courseDetails.toLearnList)

        course_detailed_description.text = getString(R.string.detailed_description)

        course_requirements_recycler.adapter = CourseWhatYouLearnAdapter(courseDetails.requirementsList)
        course_curriculum.text = getString(R.string.course_curriculum,courseDetails.lectures,courseDetails.time)
        course_curriculum_recycler.adapter = CurriculumAdapter(courseDetails.curriculum,this)

        val teacher = courseDetails.teacher
        course_teacher.text = getString(R.string.teacher,teacher.name)
        Picasso.get()
            .load(teacher.imageUrl)
            .into(course_teacher_image)
        val studentLength = getString(R.string.students,teacher.students).split(" ").get(0).length
        val courseLength = getString(R.string.course,teacher.courses).split(" ").get(0).length
        val ratingLength = getString(R.string.rating,teacher.rating.toString()).split(" ").get(0).length

        val studentsText = SpannableString(getString(R.string.students,teacher.students))
        studentsText.setSpan(ForegroundColorSpan(getColor(R.color.black)),0,studentLength,0)
        teachers_student.text = studentsText

        val courseText = SpannableString(getString(R.string.course,teacher.courses))
        courseText.setSpan(ForegroundColorSpan(getColor(R.color.black)),0,courseLength,0)
        teachers_courses.text = courseText

        val ratingText = SpannableString(getString(R.string.rating,teacher.rating.toString()))
        ratingText.setSpan(ForegroundColorSpan(getColor(R.color.black)),0,ratingLength,0)
        teachers_rating.text = ratingText

        detailed_description_expand_collapse.setOnClickListener {
            if (detailed_description_expand_collapse.text == "SHOW MORE") {
                course_detailed_description.maxLines = Int.MAX_VALUE
                detailed_description_expand_collapse.text = "SHOW LESS"
            } else {
                course_detailed_description.maxLines = 8
                detailed_description_expand_collapse.text = getString(R.string.show_more)
            }
        }

        val layout =  course_what_wil_i_learn_recycler.layoutParams
        what_to_learn_expand_collapse.setOnClickListener {
            if (what_to_learn_expand_collapse.text == "SHOW MORE") {
                layout.height = WindowManager.LayoutParams.WRAP_CONTENT
                course_what_wil_i_learn_recycler.layoutParams = layout
                what_to_learn_expand_collapse.text = "SHOW LESS"
            } else {
                layout.height = resources.getDimension(R.dimen.recycler_height).toInt()
                course_what_wil_i_learn_recycler.layoutParams = layout
                what_to_learn_expand_collapse.text = getString(R.string.show_more)
            }
        }
    }
}