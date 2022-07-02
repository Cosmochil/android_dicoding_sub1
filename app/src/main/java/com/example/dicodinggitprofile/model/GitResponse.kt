package com.example.dicodinggitprofile.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class GitResponse(
    val items : ArrayList<Users>
)
