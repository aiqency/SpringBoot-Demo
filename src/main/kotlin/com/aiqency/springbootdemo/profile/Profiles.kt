package com.aiqency.springbootdemo.profile

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

interface ProfileInt {
    val name: String
}

@Component
@Profile("dev")
class Dev: ProfileInt {
    override val name = "dev"
}

@Component
@Profile("prod")
class Prod: ProfileInt {
    override val name = "prod"
}