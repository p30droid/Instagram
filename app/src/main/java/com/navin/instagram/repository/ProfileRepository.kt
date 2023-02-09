package com.navin.instagram.repository

import androidx.lifecycle.MutableLiveData
import com.navin.instagram.models.UserInformation
import com.navin.radiojavan.api.ApiService
import com.navin.radiojavan.api.IService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileRepository() {

    private  var mutableProfile: MutableLiveData<UserInformation> = MutableLiveData<UserInformation>()

    companion object {
        val instance = ProfileRepository()
    }

    fun getUserInformation(userId : String) : MutableLiveData<UserInformation> {
        val iService: IService = ApiService.retrofit.create(IService::class.java)
        GlobalScope.launch {
            val response = iService.gerUserInformation(userId)
            mutableProfile?.postValue(response.body())
        }
        return  mutableProfile
    }


}