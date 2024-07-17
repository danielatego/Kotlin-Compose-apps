package com.example.bookshelf.data

import com.example.bookshelf.network.BookData
import com.example.bookshelf.network.BookShelfApiService
import com.example.bookshelf.network.Items

interface BookShelfPhotosRepository {
    suspend fun getBooksPhotos(): Items
}

class NetworkBookShelfPhotosRepository(
    private val bookShelfApiService: BookShelfApiService
): BookShelfPhotosRepository{
    override suspend fun getBooksPhotos(): Items {
        return bookShelfApiService.getBooksPhotos()
    }
}