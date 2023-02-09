package com.navin.instagram.ui.home.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import coil.load
import coil.transform.CircleCropTransformation
import com.navin.instagram.R
import com.navin.instagram.models.Post
import com.navin.instagram.util.Constants

class PostsAdapter(list : List<Post>) : RecyclerView.Adapter<PostsAdapter.PostsVH>() {

    var postList = list


    class PostsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imgProfile = itemView.findViewById<AppCompatImageView>(R.id.img_profile)
        val txtUsername = itemView.findViewById<AppCompatTextView>(R.id.txt_username)
        val viewPager = itemView.findViewById<ViewPager>(R.id.view_pager)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsVH {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.posts_row,null)
        return PostsVH(view)
    }

    override fun onBindViewHolder(holder: PostsVH, position: Int) {

        val post = postList.get(position)
        holder.txtUsername.setText(post.username)
        holder.imgProfile.load("${Constants.BASE_URL}${post.imageProfile}"){
            transformations(CircleCropTransformation())
        }
        holder.viewPager.adapter = ImagesAdapter(post.images)

    }

    override fun getItemCount(): Int {
      return  postList.size
    }
}