package com.navin.instagram.ui.login

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.navin.instagram.models.LoginModel
import com.navin.instagram.models.State
import com.navin.radiojavan.api.ApiService
import com.navin.radiojavan.api.IService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application) { //AndroidViewModel

    var app = application
    lateinit var mutableList: MutableLiveData<LoginModel>
    lateinit var mutableLoginState: MutableLiveData<Boolean>


    fun login(username: String, password: String): MutableLiveData<LoginModel> {
        mutableList = MutableLiveData();
        loginUser(username, password)
        return mutableList
    }

    fun loginUser(username: String, password: String) {

        val iService: IService = ApiService.retrofit.create(IService::class.java)

        GlobalScope.launch {
           val response  =  iService.loginUser(username,password)
            if(response.body()!=null) {

                saveLoginState(true, response.body()!!.user.id)
                mutableList.postValue( response.body())
            }
        }



       /* iService.login(username, password).enqueue(object : Callback<LoginModel> {
            override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {

                if (response.body()?.data!!.state == State.SUCCESS.state) {
                    saveLoginState(true)
                }
                mutableList.value = response.body()
            }

            override fun onFailure(call: Call<LoginModel>, t: Throwable) {

            }

        })*/


    }

    fun isLogin() : MutableLiveData<Boolean> {

        mutableLoginState = MutableLiveData()
        val pref: SharedPreferences = app.getSharedPreferences("setting", Context.MODE_PRIVATE)
        val state = pref.getBoolean("state", false)
        mutableLoginState.value = state
      //  return state
        return  mutableLoginState
    }

    private fun saveLoginState(state: Boolean, userId : String) {
        val pref: SharedPreferences = app.getSharedPreferences("setting", Context.MODE_PRIVATE)
        val editor: Editor = pref.edit()
        editor.putBoolean("state", state)
        editor.putString("userId",userId)
        editor.commit()
    }


}