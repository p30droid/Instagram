package com.navin.instagram.ui.home.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.navin.instagram.databinding.FragmentHomeBinding
import com.navin.instagram.models.Story
import com.navin.instagram.util.Constants
import com.navin.instagram.viewModel.PostsViewModel


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
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
        binding = FragmentHomeBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(PostsViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list: MutableList<Story> = mutableListOf()
        list.add(Story("Maryam","${Constants.BASE_URL}story/maryam.jpg"))
        list.add(Story("Jordan","${Constants.BASE_URL}story/jordan.jpg"))
        list.add(Story("Leila","${Constants.BASE_URL}story/leila.jpg"))
        list.add(Story("Sara","${Constants.BASE_URL}story/sara.jpg"))
        list.add(Story("Ravi","${Constants.BASE_URL}story/ravi.jpeg"))
        Log.e("IMAGe","${Constants.BASE_URL}story/maryam.jpg")

        binding.recyclerStory.adapter = StoryAdapter(requireContext(),list)
        binding.recyclerStory.layoutManager = LinearLayoutManager(requireContext() ,
                            LinearLayoutManager.HORIZONTAL , false)


        viewModel.getPosts().observe(owner , Observer {
            Log.e("","")
            binding.recyclerPosts.adapter = PostsAdapter(it.post)
            binding.recyclerPosts.layoutManager = LinearLayoutManager(
                requireContext() , LinearLayoutManager.VERTICAL , false)
        })


    }
}