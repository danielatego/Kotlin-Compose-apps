package com.example.courses.topic

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val categoryName: Int,
    val numberOfCourses: Int,
    @DrawableRes val categoryImage: Int,
)
