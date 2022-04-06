package com.example.testtaskpingocean.data.entity

data class LoginResponse(
    val token: String,
    val sections: List<String>
)