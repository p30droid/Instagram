package com.navin.instagram.ui.home.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.navin.instagram.R
import com.navin.instagram.models.Story


class StoryAdapter(mContext: Context , foods : List<Story>) : RecyclerView.Adapter<StoryAdapter.StoryVh>() {

    val  context = mContext
    var storyList =  foods



    class StoryVh(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtName = itemView.findViewById<AppCompatTextView>(R.id.txt_name)
        val imgStory = itemView.findViewById<AppCompatImageView>(R.id.img_story)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryVh {

        val view : View = LayoutInflater.from(context).inflate(R.layout.story_row , null)
        return StoryVh(view)

    }

    override fun onBindViewHolder(holder: StoryVh, position: Int) {

        var story : Story =  storyList.get(position);


        holder.imgStory.load(story.image){
            transformations(CircleCropTransformation())
        }
        holder.txtName.setText(story.title)



    }

    override fun getItemCount(): Int {

        return  storyList.size
    }
}