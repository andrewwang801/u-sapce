package com.example.uspace.bindingadapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.uspace.R
import com.example.uspace.ui.models.Crew
import com.example.uspace.ui.models.Measurement
import com.example.uspace.ui.models.Weight
import java.text.NumberFormat

@BindingAdapter("rocketHeight")
fun TextView.addRocketHeight(height: Measurement) {
    text = context.getString(R.string.rocket_height_m, height.meters)
}

@BindingAdapter("rocketWeight")
fun TextView.addRocketWeight(weight: Weight) {
    val formattedWeight = NumberFormat.getInstance().format(weight.kg)
    text = context.getString(R.string.rocket_weight_kg, formattedWeight)
}

@BindingAdapter("numberOfLaunches")
fun TextView.numberOfLaunches(crew: Crew) {
    val launches = crew.launches.count()
    text = context.getString(R.string.launches, launches)
}