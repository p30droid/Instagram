package com.navin.radiojavan.api

import com.navin.instagram.models.LoginModel
import com.navin.instagram.models.Posts
import com.navin.instagram.models.UserInformation
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface IService {

    @POST("login.php")
    @FormUrlEncoded
    fun login(@Field("username") user: String,
              @Field("password") pass: String): Call<LoginModel>

    @POST("login.php")
    @FormUrlEncoded
    suspend  fun loginUser(@Field("username") user: String,
              @Field("password") pass: String): Response<LoginModel>


    @GET("posts.php")
    fun getPosts(): Call<Posts>

    @POST("user_information.php")
    @FormUrlEncoded
    suspend  fun gerUserInformation(@Field("id")  id : String):
            Response<UserInformation>


    @POST("myPosts.php")
    @FormUrlEncoded
    suspend  fun getMyPosts(@Field("id") userId : String,
                           @Field("from") from  : Int ,
                           @Field("to") to : Int): Response<Posts>




}