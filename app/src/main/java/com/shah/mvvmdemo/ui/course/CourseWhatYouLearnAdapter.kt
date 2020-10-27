package com.shah.mvvmdemo.ui.course

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shah.mvvmdemo.R

class CourseWhatYouLearnAdapter(private val toLearnList: ArrayList<String>) : RecyclerView.Adapter<CourseWhatYouLearnAdapter.CourseWhatYouLearnViewHolder>() {

    class CourseWhatYouLearnViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val label = itemView.findViewById(R.id.course_what_wil_i_learn) as TextView
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseWhatYouLearnViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_what_will_i_learn, parent, false)
        return CourseWhatYouLearnViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseWhatYouLearnViewHolder, position: Int) {
        holder.label.text = toLearnList[position]
    }

    override fun getItemCount(): Int {
       return toLearnList.size
    }

}