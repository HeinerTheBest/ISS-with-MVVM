package com.mobileapps.internationalspacestationpassesmvvm.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobileapps.internationalspacestationpassesmvvm.R
import com.mobileapps.internationalspacestationpassesmvvm.viewmodel.ItemListViewModel
import com.mobileapps.internationalspacestationpassesmvvm.views.adapter.IssAdapter
import kotlinx.android.synthetic.main.activity_main.*

class ItemListActivity : AppCompatActivity() {

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
            adapter = IssAdapter(viewModel!!.response)
            rvIssResponse.layoutManager  = LinearLayoutManager(this)
            recyclerView.adapter = adapter
        }
        )
    }

}
