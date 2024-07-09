package com.example.mycity.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItemContent(
    val destinationCategory: DestinationCategory,
    @DrawableRes val icon: Int,
    @StringRes val text: Int,
)
