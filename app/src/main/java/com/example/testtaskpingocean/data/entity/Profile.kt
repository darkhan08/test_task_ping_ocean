package com.example.testtaskpingocean.data.entity

import androidx.annotation.StringRes

data class ProfileItem(
    @StringRes
    val title: Int,
    val value: String?
)