package com.aiqency.springbootdemo.springsecurity.rest

import com.aiqency.springbootdemo.springsecurity.contracts.AuthRequest
import com.aiqency.springbootdemo.springsecurity.contracts.AuthResponse
import com.aiqency.springbootdemo.springsecurity.contracts.AuthTokenIssuer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/")
class AuthRest {

    @Autowired
    lateinit var tokenIssuer: AuthTokenIssuer

    @RequestMapping("/auth", method = [RequestMethod.POST])
    fun auth(@RequestBody authRequest: AuthRequest): ResponseEntity<AuthResponse> =
            ResponseEntity.ok(tokenIssuer.authenticate(authRequest))

}

