package com.example.bookshelf.fake

import com.example.bookshelf.data.BookShelfPhotosRepository
import com.example.bookshelf.network.Items

class FakeNetworkBookShelfPhotosRepository: BookShelfPhotosRepository {
    override suspend fun getBooksPhotos(): Items {
        return FakeDataSource.booksList
    }
}