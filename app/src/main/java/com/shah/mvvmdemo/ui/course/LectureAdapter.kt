package com.shah.mvvmdemo.ui.course

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shah.mvvmdemo.R
import com.shah.mvvmdemo.data.response.LectureDetails

class LectureAdapter(private val lecturesList: List<LectureDetails>, val context: Context) : RecyclerView.Adapter<LectureAdapter.LectureViewHolder>() {

    class LectureViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById(R.id.lecture_title) as TextView
        val number = itemView.findViewById(R.id.lecture_number) as TextView
        val typeAndLength = itemView.findViewById(R.id.lecture_type_length) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LectureViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_lecture_recycler, parent, false)
        return LectureViewHolder(view)
    }

    override fun onBindViewHolder(holder: LectureViewHolder, position: Int) {
        val lecture = lecturesList[position]
        holder.title.text = lecture.title
        holder.number.text = lecture.number.toString()
        holder.typeAndLength.text = context.getString(R.string.lecture_type_length,lecture.type,lecture.length)
    }

    override fun getItemCount(): Int {
        return lecturesList.size
    }
}