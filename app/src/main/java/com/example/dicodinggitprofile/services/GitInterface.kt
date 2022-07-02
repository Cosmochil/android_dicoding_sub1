package com.example.dicodinggitprofile.services

import com.example.dicodinggitprofile.model.GitResponse
import retrofit2.Call
import retrofit2.http.*

interface GitInterface {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun findUser(
        @Query("q") query: String
    ): Call<GitResponse>
}