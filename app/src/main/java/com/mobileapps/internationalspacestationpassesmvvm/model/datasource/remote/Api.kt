package com.mobileapps.internationalspacestationpassesmvvm.model.datasource.remote

import com.mobileapps.internationalspacestationpassesmvvm.model.Iss.IssResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api
{
    @GET(ApiHelper.ISS_RESPONSE_PATH)
    fun getIssResponse(
        @Query(ApiHelper.QUERY_LAT) lat : String,
        @Query(ApiHelper.QUERY_LONG) long : String
    ) :  Call<IssResponse>
}