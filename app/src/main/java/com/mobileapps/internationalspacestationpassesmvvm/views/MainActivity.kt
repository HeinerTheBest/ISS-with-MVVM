package com.mobileapps.internationalspacestationpassesmvvm.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobileapps.internationalspacestationpassesmvvm.R
import com.mobileapps.internationalspacestationpassesmvvm.model.Iss.Response
import com.mobileapps.internationalspacestationpassesmvvm.views.adapter.IssAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var responseList : ArrayList<Response> = ArrayList()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: IssAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this)
        rvIssResponse.layoutManager = linearLayoutManager
        adapter = IssAdapter(responseList)
        rvIssResponse.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        if(responseList.size == 0)
            requestResponse()
    }

    private fun requestResponse() {
        //Mock
        responseList.add(Response(99, 1570778593))
        responseList.add(Response(585, 1570784086))
        responseList.add(Response(650, 1570789832))
        responseList.add(Response(650, 1570795641))
        responseList.add(Response(649, 1570801452))
    }

}
