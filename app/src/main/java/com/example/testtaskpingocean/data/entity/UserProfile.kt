package com.example.testtaskpingocean.data.entity

import com.google.gson.annotations.SerializedName

data class UserProfile(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val avatar: String,
    val position: String,
    @SerializedName("company_name")
    val companyName: String,
    @SerializedName("name_eng")
    val nameEng: String,
    @SerializedName("timezone")
    val timeZone: String,
    val section: List<String>,
    @SerializedName("alert_email")
    val alertEmail: String,
    @SerializedName("send_system_notifications")
    val notifications: Boolean
)