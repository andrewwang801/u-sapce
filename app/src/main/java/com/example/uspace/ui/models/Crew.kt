package com.example.uspace.ui.models

data class Crew(
    val name: String,
    val agency: String,
    val image: String,
    val externalLink: String,
    val status: String,
    val launches: List<String>
)
