package com.example.incremental


data class UserDTO(
    val id: Long,
    val points: Int,
    val clickStrength: Int,
    val totalPoints: Int,
    val totalClicks: Int,
    val name: String

)