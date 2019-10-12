package com.mobileapps.internationalspacestationpassesmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobileapps.internationalspacestationpassesmvvm.model.Iss.IssResponse
import com.mobileapps.internationalspacestationpassesmvvm.model.datasource.remote.Api
import com.mobileapps.internationalspacestationpassesmvvm.model.datasource.remote.ApiHelper
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class ItemListViewModel : ViewModel()
{
    private lateinit var list : MutableLiveData<IssResponse>
    fun getList() : LiveData<IssResponse>
    {
        if(!::list.isInitialized)
        {
            list = MutableLiveData()
            loadList()
        }
        return list
    }

    private fun loadList() {
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiHelper.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

         val api = retrofit.create(Api::class.java)
         val call : Call<IssResponse> = api.getIssResponse("50","30")

        call.enqueue(object : Callback<IssResponse> {
            override fun onFailure(call: Call<IssResponse>, t: Throwable) {
                //SOmething bad happened
                Log.d("Heiner","Failure  $t ${t.message} ")
            }

            override fun onResponse(call: Call<IssResponse>, response: Response<IssResponse>) {
                if (response.isSuccessful) {
                    list.value = response.body()
                    Log.d("Heiner","Something Good")
                } else {
                   //SOmething else
                    Log.d("Heiner","Something else")
                }
            }
        })

    }
}