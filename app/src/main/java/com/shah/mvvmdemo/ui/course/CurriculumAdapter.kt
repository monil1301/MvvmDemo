package com.shah.mvvmdemo.ui.course

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shah.mvvmdemo.R
import com.shah.mvvmdemo.data.response.CurriculumDetails

class CurriculumAdapter(
    private val curriculumList: ArrayList<CurriculumDetails>,
    private val context: Context
) : RecyclerView.Adapter<CurriculumAdapter.CurriculumViewHolder>() {

    class CurriculumViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById(R.id.curriculum_title) as TextView
        val expandCollapseButton = itemView.findViewById(R.id.expand_collapse_button) as ImageButton
        val lectureRecyclerView = itemView.findViewById(R.id.course_lectures_recycler) as RecyclerView
        val expandCollapse = itemView.findViewById(R.id.expand_collapse) as ConstraintLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurriculumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_curriculum_recycler, parent, false)
        return CurriculumViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurriculumViewHolder, position: Int) {
        val curriculum = curriculumList[position]
        holder.title.text = curriculum.title
        if (curriculum.isExpanded) {
            holder.expandCollapseButton.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_show_less))
            holder.lectureRecyclerView.adapter = LectureAdapter(curriculum.lectures,context)
            holder.lectureRecyclerView.visibility = RecyclerView.VISIBLE
        } else {
            holder.expandCollapseButton.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_show_more))
            holder.lectureRecyclerView.visibility = RecyclerView.GONE
        }
        holder.expandCollapse.setOnClickListener {
            curriculum.isExpanded = (!curriculum.isExpanded)
            notifyItemChanged(position)
        }
        holder.expandCollapseButton.setOnClickListener {
            curriculum.isExpanded = (!curriculum.isExpanded)
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return curriculumList.size
    }
}