package com.example.mycity.data

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItemContent(
    val destinationCategory: DestinationCategory,
    val icon: ImageVector,
    @StringRes val text: Int,
)
