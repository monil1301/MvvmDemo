package com.shah.mvvmdemo.ui.featured

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shah.mvvmdemo.R
import com.shah.mvvmdemo.data.response.CourseCategory
import com.squareup.picasso.Picasso

class ParentRecyclerAdapter(private val context: Context, private val categoryList: ArrayList<CourseCategory?>, private val onClick: InnerRecyclerAdapter.OnClick) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ParentRecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById(R.id.inner_recycler_title) as TextView
        val recyclerView = itemView.findViewById(R.id.inner_recycler_view) as RecyclerView
    }
    
    class ImageBannerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageBanner = itemView.findViewById(R.id.image_banner) as ImageView
    } 

    override fun getItemViewType(position: Int): Int {
        if (categoryList[position] == null) {
            return 0
        }
        return 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_featured_recycler, parent, false)
            return ParentRecyclerViewHolder(view)
        }
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_banner, parent, false)
        return ImageBannerViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val category = categoryList[position]
        if (category != null) {
            val holder = viewHolder as ParentRecyclerViewHolder
            holder.title.text = category.title
            holder.recyclerView.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            holder.recyclerView.adapter = InnerRecyclerAdapter(category.courses,onClick,context)
        } else {
            val holder = viewHolder as ImageBannerViewHolder
            Picasso.get()
                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTPtXpojuoZld0fN8dIUtIoUQ9tTO6AD53jOg&usqp=CAU")
                .into(holder.imageBanner)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

}