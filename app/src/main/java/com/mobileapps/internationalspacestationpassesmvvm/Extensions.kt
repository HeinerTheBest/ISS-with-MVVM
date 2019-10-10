package com.mobileapps.internationalspacestationpassesmvvm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import java.sql.Timestamp
import java.util.*

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

 fun Int.convertToDate(): String
{
    val ts = Timestamp(this.toString().toLong())
    val date = Date(ts.time)
    return date.toString()
}

