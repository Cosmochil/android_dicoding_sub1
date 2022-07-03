package com.example.dicodinggitprofile.services

import com.example.dicodinggitprofile.model.GitResponse
import com.example.dicodinggitprofile.model.UserInfoResponse
import com.example.dicodinggitprofile.model.Users
import retrofit2.Call
import retrofit2.http.*

interface GitInterface {
    @GET("search/users")
    @Headers("Authorization: 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")
    fun findUser(
        @Query("q") query: String
    ): Call<GitResponse>

    @GET("/users/{username}")
    @Headers("Authorization: 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")
    fun getUserDetails(
        @Path("username") username: String
    ): Call<UserInfoResponse>

    @GET("/users/{username}/followers")
    @Headers("Authorization: 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<Users>>

    @GET("/users/{username}/following")
    @Headers("Authorization: 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<Users>>
}