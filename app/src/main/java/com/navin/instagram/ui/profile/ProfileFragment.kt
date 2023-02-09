package com.navin.instagram.ui.profile

import android.content.Context
import android.content.SharedPreferences
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
import coil.load
import coil.transform.CircleCropTransformation
import com.navin.instagram.databinding.FragmentProfileBinding
import com.navin.instagram.models.Story
import com.navin.instagram.ui.profile.posts.PostsFragment
import com.navin.instagram.ui.profile.reels.ReelsFragment
import com.navin.instagram.ui.home.ViewPagerAdapter
import com.navin.instagram.util.Constants
import com.navin.instagram.ui.home.home.StoryAdapter
import com.navin.instagram.viewModel.ProfileViewModel


class ProfileFragment : Fragment() {

    lateinit var binding : FragmentProfileBinding

    lateinit var  viewModel: ProfileViewModel
    lateinit var  owner: LifecycleOwner

    override fun onAttach(context: Context) {
        super.onAttach(context)
        owner = this
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgProfile.load("${Constants.BASE_URL}story/maryam.jpg"){
            transformations(CircleCropTransformation())
        }


        val list: MutableList<Story> = mutableListOf()
        list.add(Story("Maryam","${Constants.BASE_URL}story/maryam.jpg"))
        list.add(Story("Jordan","${Constants.BASE_URL}story/jordan.jpg"))
        list.add(Story("Leila","${Constants.BASE_URL}story/leila.jpg"))
        list.add(Story("Sara","${Constants.BASE_URL}story/sara.jpg"))
        list.add(Story("Ravi","${Constants.BASE_URL}story/ravi.jpeg"))
        Log.e("IMAGe","${Constants.BASE_URL}story/maryam.jpg")

        binding.recyclerHighlit.adapter = StoryAdapter(requireContext(),list)
        binding.recyclerHighlit.layoutManager = LinearLayoutManager(requireContext() ,
            LinearLayoutManager.HORIZONTAL , false)


        val adapter = ViewPagerAdapter(requireActivity().supportFragmentManager)

        // add fragment to the list
        adapter.addFragment(PostsFragment(), "Posts")
        adapter.addFragment(ReelsFragment(), "Reels")

        // Adding the Adapter to the ViewPager
        binding.viewPager.adapter = adapter

        // bind the viewPager with the TabLayout.
        binding.tabLayout.setupWithViewPager( binding.viewPager)

        val pref: SharedPreferences = requireActivity().getSharedPreferences("setting",
            Context.MODE_PRIVATE)
        var userId = pref.getString("userId","")
        binding.progressBar.visibility = View.VISIBLE
        viewModel.profileUser(userId!!).observe(owner , Observer {user->
            binding.progressBar.visibility = View.GONE
            binding.constraintProfile.visibility = View.VISIBLE
            Log.e("","")

            binding.imgProfile.load("${Constants.BASE_URL}${user.image}"){
                transformations(CircleCropTransformation())
            }
            binding.txtPostsCount.text = user.posts_count.toString()
            binding.txtUsername.text = user.username.toString()
            binding.txtBio.text = user.bio.toString()

        })



    }

}