package com.example.bookshelf.fake

import com.example.bookshelf.network.BookData
import com.example.bookshelf.network.ImageLinks
import com.example.bookshelf.network.Items
import com.example.bookshelf.network.Thumbnail

object FakeDataSource {
    val booksList: Items = Items(
        listOf<BookData>(
            BookData(
                id="1",
                volumeInfo = ImageLinks(
                    imageLinks = Thumbnail(
                        "http://books.google.com/books/content?id=zrjMsgEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
                    )
                )
            ),
            BookData(
                id="2",
                volumeInfo = ImageLinks(
                    imageLinks = Thumbnail(
                        "http://books.google.com/books/content?id=cOizAAAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
                    )
                )
            ),BookData(
                id="3",
                volumeInfo = ImageLinks(
                    imageLinks = Thumbnail(
                        "http://books.google.com/books/content?id=VZdCzwEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
                    )
                )
            ),BookData(
                id="4",
                volumeInfo = ImageLinks(
                    imageLinks = Thumbnail(
                        "http://books.google.com/books/content?id=fiqTEAAAQBAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
                    )
                )
            ),
        )
    )
}