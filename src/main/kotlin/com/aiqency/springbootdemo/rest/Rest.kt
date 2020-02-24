package com.aiqency.springbootdemo.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/greeting")
class Rest(@Autowired val greeting: Greeting) {

    @GetMapping("/{foobar}", produces = ["text/plain"])
    fun greeting(@PathVariable foobar: String) = "${greeting.hello} ${foobar.capitalize()}!"

}