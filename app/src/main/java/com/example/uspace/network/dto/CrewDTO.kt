package com.example.uspace.network.dto

import com.example.uspace.ui.models.Crew
import com.google.gson.annotations.SerializedName

data class CrewDTO(
    val name: String,
    val agency: String,
    val image: String,
    @SerializedName("wikipedia")
    val externalLink: String,
    val status: String,
    val launches: List<String>
)

fun CrewDTO.toCrew() = Crew(name, agency, image, externalLink, status, launches)
