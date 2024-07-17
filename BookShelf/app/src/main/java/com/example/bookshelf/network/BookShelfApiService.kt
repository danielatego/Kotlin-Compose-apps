package com.example.bookshelf.network

import retrofit2.http.GET

interface BookShelfApiService {
    @GET("/books/v1/volumes?q=inauthor:fuller")
    suspend fun getBooksPhotos(): Items
}