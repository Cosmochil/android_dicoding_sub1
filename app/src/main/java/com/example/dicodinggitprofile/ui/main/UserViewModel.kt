package com.example.dicodinggitprofile.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dicodinggitprofile.model.GitResponse
import com.example.dicodinggitprofile.model.Users
import com.example.dicodinggitprofile.services.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel: ViewModel() {
    val userList = MutableLiveData<ArrayList<Users>>()

    fun setSearchUser(query: String) {
        Client.apiInstance
            .findUser(query)
            .enqueue(object : Callback<GitResponse>{
                override fun onResponse(call: Call<GitResponse>, response: Response<GitResponse>) {
                    if (response.isSuccessful) {
                        userList.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<GitResponse>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }

    fun getSearchUser(): LiveData<ArrayList<Users>>{
        return userList
    }
}