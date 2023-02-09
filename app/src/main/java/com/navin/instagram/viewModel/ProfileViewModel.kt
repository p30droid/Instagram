package com.navin.instagram.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.navin.instagram.models.UserInformation
import com.navin.instagram.repository.ProfileRepository

class ProfileViewModel() : ViewModel() {


    private val repository : ProfileRepository =  ProfileRepository.instance

    fun profileUser( userId : String) : MutableLiveData<UserInformation> = repository.getUserInformation(userId)


}