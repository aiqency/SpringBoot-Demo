package com.aiqency.springbootdemo.rest

import org.springframework.stereotype.Service

interface Greeting {
    val hello: String
}

@Service
class Greetings: Greeting {
    override val hello: String = "Hello"
}