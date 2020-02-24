package com.aiqency.springbootdemo.springdata.repositories

import com.aiqency.springbootdemo.springdata.model.BookEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ReadingListRepositoryInt : JpaRepository<BookEntity, Long> {
    fun findByAuthor(author: String): MutableList<BookEntity>? = null
}