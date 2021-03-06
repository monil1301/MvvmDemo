package com.shah.mvvmdemo.ui.home.featured

import android.content.Context
import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shah.mvvmdemo.R
import com.shah.mvvmdemo.data.response.CourseDetails
import com.squareup.picasso.Picasso

class InnerRecyclerAdapter(
    private val courses: List<CourseDetails>,
    private val onClick: OnClick,
    private val context: Context
) : RecyclerView.Adapter<InnerRecyclerAdapter.InnerRecyclerViewHolder>() {

    interface OnClick{
        fun gotoDetails(course: CourseDetails)
    }

    class InnerRecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView = itemView.findViewById(R.id.course_image_view) as ImageView
        val title = itemView.findViewById(R.id.course_title) as TextView
        val ratingBar = itemView.findViewById(R.id.course_rating) as RatingBar
        val rating = itemView.findViewById(R.id.course_rating_num) as TextView
        val totalRatings = itemView.findViewById(R.id.course_total_ratings) as TextView
        val teacher = itemView.findViewById(R.id.course_teacher) as TextView
        val price = itemView.findViewById(R.id.course_price) as TextView
        val cancelledPrice = itemView.findViewById(R.id.course_cancelled_price) as TextView
        val bestseller = itemView.findViewById(R.id.isBestSeller) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_featured_inner_recycler,parent,false)
        return InnerRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: InnerRecyclerViewHolder, position: Int) {
        val course = courses[position]
        val cancelledPrice = SpannableString(context.getString(R.string.Rs, course.cancelledPrice))
        cancelledPrice.setSpan(StrikethroughSpan(),0,cancelledPrice.toString().length,0)
        Picasso.get()
            .load(course.imageUrl)
            .into(holder.imageView)
        holder.title.text = course.title
        holder.teacher.text = course.teacher.name
        holder.ratingBar.rating = course.rating
        holder.rating.text = course.rating.toString()
        holder.totalRatings.text = context.getString(R.string.rating, course.totalRatings)
        holder.price.text = context.getString(R.string.Rs, course.price)
        holder.cancelledPrice.text = cancelledPrice
        if (course.isBestseller){
            holder.bestseller.visibility = TextView.VISIBLE
        } else {
            holder.bestseller.visibility = TextView.GONE
        }
        holder.itemView.setOnClickListener {
            onClick.gotoDetails(course)
        }
    }

    override fun getItemCount(): Int {
        return courses.size
    }

}