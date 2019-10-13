package com.mobileapps.internationalspacestationpassesmvvm.viewmodel

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mobileapps.internationalspacestationpassesmvvm.model.Iss.IssResponse
import com.mobileapps.internationalspacestationpassesmvvm.model.datasource.remote.Api
import com.mobileapps.internationalspacestationpassesmvvm.model.datasource.remote.ApiHelper
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class ItemListViewModel : ViewModel()
{
    private lateinit var list : MutableLiveData<IssResponse>
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var context: Context
    private val PERMISSION_ID = 42




    fun getList(context: Context) : LiveData<IssResponse>
    {   this.context = context
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        if(!::list.isInitialized)
        {
            list = MutableLiveData()
            startPermission()
        }
        return list
    }

    private fun loadList(lat:String,long: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiHelper.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

         val api = retrofit.create(Api::class.java)
         val call : Call<IssResponse> = api.getIssResponse(lat,long)

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

    private fun startPermission() {
        if (checkPermission(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            fusedLocationClient.lastLocation?.addOnSuccessListener {
                location ->
                if(location == null){
                    //todo do something here letter
                }
                else location.apply {
                    loadList(location.latitude.toString(),location.longitude.toString())
                }
            }
        }
    }

    private fun checkPermission(vararg perm:String) : Boolean {
        val havePermissions = perm.toList().all {
            ContextCompat.checkSelfPermission(context,it) ==
                    PackageManager.PERMISSION_GRANTED
        }
        if (!havePermissions) {
            if(perm.toList().any {
                    ActivityCompat.
                        shouldShowRequestPermissionRationale(context as Activity, it)}
            ) {
                val dialog = AlertDialog.Builder(context)
                    .setTitle("Permission")
                    .setMessage("Permission needed!")
                    .setPositiveButton("OK") { _, _ ->
                        ActivityCompat.requestPermissions(context as Activity, perm, PERMISSION_ID)
                        startPermission()
                    }
                    .setNegativeButton("No") { _, _ -> }
                    .create()
                dialog.show()
            } else {
                ActivityCompat.requestPermissions(context as Activity, perm, PERMISSION_ID)
                startPermission()
            }
            return false
        }
        return true
    }


}