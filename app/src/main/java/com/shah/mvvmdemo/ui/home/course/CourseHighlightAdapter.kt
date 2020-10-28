package com.shah.mvvmdemo.ui.home.course

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shah.mvvmdemo.R

class CourseHighlightAdapter(private val highlights: ArrayList<String>) : RecyclerView.Adapter<CourseHighlightAdapter.CourseHighlightViewHolder>() {

    class CourseHighlightViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val label = itemView.findViewById(R.id.label) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseHighlightViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_course_hightlight, parent, false)
        return CourseHighlightViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseHighlightViewHolder, position: Int) {
        holder.label.text = highlights[position]
    }

    override fun getItemCount(): Int {
        return highlights.size
    }


}