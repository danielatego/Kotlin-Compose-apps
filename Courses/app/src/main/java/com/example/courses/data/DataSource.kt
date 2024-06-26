package com.example.courses.data

import com.example.courses.R
import com.example.courses.topic.Topic

class DataSource() {

    fun LoadTopics(): List<Topic> {
        return listOf<Topic>(
            Topic(R.string.architecture, 58, R.drawable.architecture),
            Topic(R.string.crafts, 121, R.drawable.crafts),
            Topic(R.string.business, 78, R.drawable.business),
            Topic(R.string.culinary, 118, R.drawable.culinary),
            Topic(R.string.design, 423, R.drawable.design),
            Topic(R.string.fashion, 92, R.drawable.fashion),
            Topic(R.string.film, 165, R.drawable.film),
            Topic(R.string.gaming, 164, R.drawable.gaming),
            Topic(R.string.drawing, 326, R.drawable.drawing),
            Topic(R.string.lifestyle, 305, R.drawable.lifestyle),
            Topic(R.string.music, 212, R.drawable.music),
            Topic(R.string.painting, 172, R.drawable.painting),
            Topic(R.string.photography, 321, R.drawable.photography),
            Topic(R.string.tech, 118, R.drawable.tech),
            Topic(R.string.geology, 18, R.drawable.geology),
            Topic(R.string.ecology, 118, R.drawable.ecology),
            Topic(R.string.history, 118, R.drawable.history),
            Topic(R.string.law, 118, R.drawable.law),
            Topic(R.string.finance, 118, R.drawable.finance),
            Topic(R.string.engineering, 118, R.drawable.engineering),
            Topic(R.string.journalism, 118, R.drawable.journalism),
            Topic(R.string.automotive, 118, R.drawable.automotive),
            Topic(R.string.biology,18, R.drawable.biology),
            Topic(R.string.physics, 118, R.drawable.physics),
        )
    }
}