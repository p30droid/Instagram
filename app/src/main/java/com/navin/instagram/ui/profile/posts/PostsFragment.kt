package com.navin.instagram.ui.profile.posts

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.navin.instagram.R
import com.navin.instagram.databinding.FragmentPostsBinding
import com.navin.instagram.viewModel.PostsViewModel


class PostsFragment : Fragment() {


    lateinit var binding : FragmentPostsBinding
    lateinit var viewModel: PostsViewModel
    lateinit var owner: LifecycleOwner

    override fun onAttach(context: Context) {
        super.onAttach(context)
        owner = this
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostsBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(PostsViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val pref: SharedPreferences = requireActivity().getSharedPreferences("setting",
            Context.MODE_PRIVATE)
        var userId = pref.getString("userId","")
        binding.progressBar.visibility = View.VISIBLE
        viewModel.myPosts(userId!!, 0 , 20).observe(owner , {posts->
            Log.e("","")
            binding.progressBar.visibility = View.GONE
            binding.recyclerPosts.visibility = View.VISIBLE
            binding.recyclerPosts.adapter = MyPostsAdapter(posts.post)
            binding.recyclerPosts.layoutManager = GridLayoutManager(
                requireContext() , 3
            )


        })

    }

}