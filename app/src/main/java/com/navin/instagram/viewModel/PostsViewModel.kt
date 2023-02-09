package com.navin.instagram.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.navin.instagram.models.Posts
import com.navin.instagram.models.UserInformation
import com.navin.instagram.repository.PostsRepository
import com.navin.instagram.repository.ProfileRepository
import com.navin.radiojavan.api.ApiService
import com.navin.radiojavan.api.IService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsViewModel : ViewModel() {


    private  lateinit var mutableList: MutableLiveData<Posts>


    fun getPosts() : MutableLiveData<Posts> {

        mutableList = MutableLiveData<Posts>();
        loadPosts()
        return mutableList

    }
    private fun loadPosts(){
        val iService : IService = ApiService.retrofit.create(IService::class.java)

        iService.getPosts().enqueue(object : Callback<Posts> {
            override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
                mutableList.value = response.body()
            }

            override fun onFailure(call: Call<Posts>, t: Throwable) {

            }

        })

    }



    private val repository : PostsRepository =  PostsRepository.instance
    fun myPosts( userId : String, from : Int , to : Int) : MutableLiveData<Posts> =
        repository.getPosts(userId, from , to)


}