package com.navin.instagram.ui.profile.posts

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.navin.instagram.R
import com.navin.instagram.models.Post
import com.navin.instagram.util.Constants

class MyPostsAdapter(val posts : List<Post>) : RecyclerView.Adapter<MyPostsAdapter.MyPostsVH>() {

    val postsList = posts

    class MyPostsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPost = itemView.findViewById<AppCompatImageView>(R.id.img_post);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPostsVH {
        val view : View  = LayoutInflater.from(parent.context).inflate(R.layout.my_posts,null)
        return  MyPostsVH(view)
     }

    override fun onBindViewHolder(holder: MyPostsVH, position: Int) {

        val post = postsList.get(position);
        val url = "https://mobilemasters.ir/apps/instagram/images/${post.images.get(0).img}"
        Log.e("urlpost","$url")
        holder.imgPost.load(url)

     }

    override fun getItemCount(): Int {
        return  postsList.size
     }
}