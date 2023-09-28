package com.example.uspace.network.dto

import com.example.uspace.ui.models.Rocket
import com.example.uspace.ui.models.Weight
import com.google.gson.annotations.SerializedName
import com.raywenderlich.android.uspace.network.dto.MeasurementDTO
import com.raywenderlich.android.uspace.network.dto.WeightDTO
import com.raywenderlich.android.uspace.network.dto.toMeasurement
import com.raywenderlich.android.uspace.network.dto.toWeight

data class RocketDTO (
    val name: String,
    val type: String,
    val active: Boolean,
    val stages: Int,
    val boosters: Int,
    @SerializedName("cost_per_launch")
    val costPerLaunch: Long,
    @SerializedName("success_rate_pct")
    val successRate: Int,
    @SerializedName("first_flight")
    val firstFlightDate: String,
    val country: String,
    val company: String,
    @SerializedName("wikipedia")
    val externalLink: String,
    val description: String,
    @SerializedName("flickr_images")
    val images: List<String>,
    val height: MeasurementDTO,
    val diameter: MeasurementDTO,
    val mass: WeightDTO
)

fun RocketDTO.toRocket() = Rocket(
    name, type, active, stages, boosters, costPerLaunch, successRate, firstFlightDate, country,
    company, externalLink, description, images, height.toMeasurement(), diameter.toMeasurement(),
    mass.toWeight()
)