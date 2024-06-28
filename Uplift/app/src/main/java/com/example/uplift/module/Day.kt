package com.example.uplift.module

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
//data class describing the day properties
data class Day(
    @StringRes val day: Int,
    @StringRes val title: Int,
    @DrawableRes val picture: Int,
)
