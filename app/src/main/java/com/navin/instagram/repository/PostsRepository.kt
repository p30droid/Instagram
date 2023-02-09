package com.navin.instagram.repository

import androidx.lifecycle.MutableLiveData
import com.navin.instagram.models.Posts
 import com.navin.radiojavan.api.ApiService
import com.navin.radiojavan.api.IService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PostsRepository {

    private  var mutablePosts: MutableLiveData<Posts> = MutableLiveData<Posts>()

    companion object {
        val instance = PostsRepository()
    }

    fun getPosts(userId : String, from : Int , to : Int) : MutableLiveData<Posts> {
        val iService: IService = ApiService.retrofit.create(IService::class.java)
        GlobalScope.launch {
            val response = iService.getMyPosts(userId,from , to)
            mutablePosts.postValue(response.body())
        }
        return  mutablePosts
    }
}