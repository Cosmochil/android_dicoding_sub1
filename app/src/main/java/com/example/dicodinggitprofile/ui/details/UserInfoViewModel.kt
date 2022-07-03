package com.example.dicodinggitprofile.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dicodinggitprofile.model.UserInfoResponse
import com.example.dicodinggitprofile.services.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserInfoViewModel : ViewModel() {
    val user = MutableLiveData<UserInfoResponse>()

    fun setUserInfo(username: String?) {
        Client.apiInstance
            .getUserDetails(username.toString())
            .enqueue(object : Callback<UserInfoResponse> {
                override fun onResponse(
                    call: Call<UserInfoResponse>,
                    response: Response<UserInfoResponse>
                ) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }

    fun getUserInfo(): LiveData<UserInfoResponse> {
        return user

    }
}