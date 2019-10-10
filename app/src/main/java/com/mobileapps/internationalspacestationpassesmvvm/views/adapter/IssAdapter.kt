package com.mobileapps.internationalspacestationpassesmvvm.views.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobileapps.internationalspacestationpassesmvvm.R
import com.mobileapps.internationalspacestationpassesmvvm.convertToDate
import com.mobileapps.internationalspacestationpassesmvvm.inflate
import com.mobileapps.internationalspacestationpassesmvvm.model.Iss.Response
import kotlinx.android.synthetic.main.iss_item.view.*


class IssAdapter(private val responses: ArrayList<Response>) : RecyclerView.Adapter<IssAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.iss_item)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount() = responses.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemResponse = responses[position]
        holder.bindResponse(itemResponse)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v)
    {
        private var view = v
        private var response : Response? = null

        fun bindResponse(response: Response){
            this.response = response
            view.tvDuration.text = view.context.resources.getString(R.string.duration,response.duration)
            view.tvTime.text =view.context.resources.getString(R.string.time,response.risetime.convertToDate())
        }
    }
}