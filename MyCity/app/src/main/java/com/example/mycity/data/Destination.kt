package com.example.mycity.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Destination(
    @StringRes val name:Int,
    val category: DestinationCategory,
    @StringRes val description: Int,
    @DrawableRes val destinationPicture: Int
)
