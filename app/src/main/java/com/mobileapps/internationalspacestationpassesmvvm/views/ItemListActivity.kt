package com.mobileapps.internationalspacestationpassesmvvm.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobileapps.internationalspacestationpassesmvvm.R
import com.mobileapps.internationalspacestationpassesmvvm.model.Iss.IssResponse
import com.mobileapps.internationalspacestationpassesmvvm.model.Iss.Response
import com.mobileapps.internationalspacestationpassesmvvm.viewmodel.ItemListViewModel
import com.mobileapps.internationalspacestationpassesmvvm.views.adapter.IssAdapter
import kotlinx.android.synthetic.main.activity_main.*

class ItemListActivity : AppCompatActivity() {

    private var responseList : ArrayList<Response> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: IssAdapter
    private lateinit var viewModel : ItemListViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rvIssResponse)

        viewModel = ViewModelProviders.of(this).get(ItemListViewModel::class.java)

        viewModel.getList().observe(this, Observer
        {
            viewModel ->
            Log.d("Heiner",viewModel.toString())
            adapter = IssAdapter(viewModel!!.response)
            rvIssResponse.layoutManager  = LinearLayoutManager(this)
            recyclerView.adapter = adapter
        }
        )
    }

    override fun onStart() {
        super.onStart()
        if(responseList.size == 0)
            requestResponse()
    }

    private fun requestResponse() {
        //Mock
        //responseList.add(Response(99, 1570778593))
        responseList.add(Response(585, 1570784086))
        responseList.add(Response(650, 1570789832))
        responseList.add(Response(650, 1570795641))
        responseList.add(Response(649, 1570801452))
    }

}
