package com.aiqency.springbootdemo.springdata.rest

import com.aiqency.springbootdemo.springdata.repositories.ReadingListRepositoryInt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/books")
class DataRest(@Autowired val repo: ReadingListRepositoryInt) {

    @GetMapping("/{author}", produces = ["application/json"])
    fun findBooksByReader(@PathVariable author: String) = repo.findByAuthor(author)

}