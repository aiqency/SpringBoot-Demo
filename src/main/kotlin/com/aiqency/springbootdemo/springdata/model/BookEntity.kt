package com.aiqency.springbootdemo.springdata.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_book_id_pk")
    var id: Long? = null
    var reader: String? = null
    var isbn: String? = null
    var title: String? = null
    var author: String? = null
    var description: String? = null

}