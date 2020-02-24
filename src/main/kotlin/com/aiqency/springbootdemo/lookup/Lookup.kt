package com.aiqency.springbootdemo.lookup

import org.springframework.beans.factory.annotation.Lookup
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope("prototype")
class Prototype

@Component
class Singleton {
    var prototype: Prototype? = null @Lookup get
}

class A {
    fun print() {
        println("Hello")
    }
}
class B