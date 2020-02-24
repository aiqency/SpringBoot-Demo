package com.aiqency.springbootdemo.springsecurity.rest

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class SecuredRest {

    @RequestMapping("/secured/isAuth", method = [RequestMethod.GET])
    fun adminIsAuth() = ResponseEntity.ok().build<Void>()

}

