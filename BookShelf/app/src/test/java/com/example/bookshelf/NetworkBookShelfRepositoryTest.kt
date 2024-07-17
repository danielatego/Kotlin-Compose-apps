package com.example.bookshelf

import com.example.bookshelf.data.NetworkBookShelfPhotosRepository
import com.example.bookshelf.fake.FakeBookShelfApiService
import com.example.bookshelf.fake.FakeDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkBookShelfRepositoryTest {

    @Test
    fun networkBookShelfRepositosy_getBooksList_verifyBooksList() = runTest{
        val repository = NetworkBookShelfPhotosRepository(
            bookShelfApiService = FakeBookShelfApiService()
        )
        assertEquals(FakeDataSource.booksList,repository.getBooksPhotos())
    }
}