package com.navin.instagram.ui.dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.navin.instagram.R
import com.navin.instagram.databinding.FragmentDashboardBinding
import com.navin.instagram.ui.addPost.AddPostFragment
import com.navin.instagram.ui.explore.ExploreFragment
import com.navin.instagram.ui.home.home.HomeFragment
import com.navin.instagram.ui.profile.ProfileFragment
import com.navin.radiojavan.adapter.PagerAdapter

class DashboardFragment : Fragment() {

    lateinit var binding : FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = mutableListOf<Fragment>()
        list.add(HomeFragment())
        list.add(ExploreFragment())
        list.add(AddPostFragment())
        list.add(ProfileFragment())
        binding.pager.adapter = PagerAdapter(requireActivity(),list );

        binding.pager.isUserInputEnabled=false

        binding.bottomMenu.setOnItemSelectedListener { item->

            Log.e("","")
            when(item.itemId){
                R.id.item_home ->{

                    binding.pager.currentItem = 0
                    true

                }
                R.id.item_explore -> {
                    binding.pager.currentItem = 1
                    true
                }
                R.id.item_add_post -> {
                    binding.pager.currentItem = 2
                    true
                }

                R.id.item_profile -> {
                    binding.pager.currentItem = 3
                    true
                }

            }
            true
        }

        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                when(position){
                    0->binding.bottomMenu.menu.findItem(R.id.item_home).setChecked(true)
                    1->binding.bottomMenu.menu.findItem(R.id.item_explore).setChecked(true)
                    2->binding.bottomMenu.menu.findItem(R.id.item_add_post).setChecked(true)
                    3->binding.bottomMenu.menu.findItem(R.id.item_profile).setChecked(true)
                }

            }

        })

    }



}