package com.example.bookshelf.fake

import com.example.bookshelf.network.BookShelfApiService
import com.example.bookshelf.network.Items

class FakeBookShelfApiService:BookShelfApiService {
    override suspend fun getBooksPhotos(): Items {
        return FakeDataSource.booksList
    }
}