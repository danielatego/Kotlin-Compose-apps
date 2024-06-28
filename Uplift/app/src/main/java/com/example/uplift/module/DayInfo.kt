package com.example.uplift.module

import androidx.compose.ui.res.stringResource
import com.example.uplift.R

class DayInfo {
    fun listOfDaysInfo(): List<Day>{
        val list = listOf<Day>(
            Day(R.string.day_number,R.string.one,R.drawable.one),
            Day(R.string.day_number,R.string.two,R.drawable.two),
            Day(R.string.day_number,R.string.three,R.drawable.three),
            Day(R.string.day_number,R.string.four,R.drawable.four),
            Day(R.string.day_number,R.string.five,R.drawable.five),
            Day(R.string.day_number,R.string.six,R.drawable.six),
            Day(R.string.day_number,R.string.seven,R.drawable.seven),
            Day(R.string.day_number,R.string.eight,R.drawable.eight),
            Day(R.string.day_number,R.string.nine,R.drawable.nine),
            Day(R.string.day_number,R.string.ten,R.drawable.ten),
            Day(R.string.day_number,R.string.eleven,R.drawable.eleven),
            Day(R.string.day_number,R.string.twelve,R.drawable.twelve),
            Day(R.string.day_number,R.string.thirteen,R.drawable.thirteen),
            Day(R.string.day_number,R.string.fourteen,R.drawable.fouteen),
            Day(R.string.day_number,R.string.fifteen,R.drawable.fifteen),
            Day(R.string.day_number,R.string.sixteen,R.drawable.sixteen),
            Day(R.string.day_number,R.string.seventeen,R.drawable.seventeen),
            Day(R.string.day_number,R.string.eighteen,R.drawable.eighteen),
            Day(R.string.day_number,R.string.nineteen,R.drawable.nineteen),
            Day(R.string.day_number,R.string.twenty,R.drawable.twenty),
            Day(R.string.day_number,R.string.twenty_one,R.drawable.twenty_one),
            Day(R.string.day_number,R.string.twenty_two,R.drawable.twenty_two),
            Day(R.string.day_number,R.string.twenty_three,R.drawable.twenty_three),
            Day(R.string.day_number,R.string.twenty_four,R.drawable.twenty_four),
            Day(R.string.day_number,R.string.twenty_five,R.drawable.twenty_five),
            Day(R.string.day_number,R.string.twenty_six,R.drawable.twenty_six),
            Day(R.string.day_number,R.string.twenty_seven,R.drawable.twenty_seven),
            Day(R.string.day_number,R.string.twenty_eight,R.drawable.twenty_eight),
            Day(R.string.day_number,R.string.twenty_nine,R.drawable.twenty_nine),
            Day(R.string.day_number,R.string.thirty,R.drawable.thirty),

        )
        return list
    }
}