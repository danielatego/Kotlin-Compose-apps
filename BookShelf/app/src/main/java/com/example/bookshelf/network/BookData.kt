package com.example.bookshelf.network

import android.view.textclassifier.TextLinks
import kotlinx.serialization.Serializable

@Serializable
data class Items(
    val items:List<BookData>
)
@Serializable
data class BookData (
    val id: String,
    val volumeInfo: ImageLinks,
    //val imageLinks: Thumbnails
)
@Serializable
data class ImageLinks(
    val imageLinks: Thumbnail?=null
)

@Serializable
data class Thumbnail(
    val thumbnail: String
)