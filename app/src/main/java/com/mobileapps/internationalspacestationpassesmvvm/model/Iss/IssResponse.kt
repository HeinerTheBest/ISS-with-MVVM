package com.mobileapps.internationalspacestationpassesmvvm.model.Iss


import com.google.gson.annotations.SerializedName

data class IssResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("request")
    val request: Request,
    @SerializedName("response")
    val response: List<Response>
)