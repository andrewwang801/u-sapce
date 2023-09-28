package com.example.uspace.ui.models

data class Rocket(
    val name: String,
    val type: String,
    val active: Boolean,
    val stages: Int,
    val boosters: Int,
    val costPerLaunch: Long,
    val successRate: Int,
    val firstFlightDate: String,
    val country: String,
    val company: String,
    val externalLink: String,
    val description: String,
    val images: List<String>,
    val height: Measurement,
    val diameter: Measurement,
    val mass: Weight
)