package com.aiqency.springbootdemo.springsecurity.rest

import com.aiqency.springbootdemo.springsecurity.configuration.AuthUserDetailService

import com.aiqency.springbootdemo.springsecurity.contracts.AuthRequest
import com.aiqency.springbootdemo.springsecurity.contracts.AuthResponse
import com.aiqency.springbootdemo.springsecurity.contracts.AuthTokenIssuer
import com.aiqency.springbootdemo.springsecurity.jwt.JwtService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

/**
 * @param authenticationManager that has been set by the AuthenticationManagerBuilder
 * in the [com.aiqency.springbootdemo.springsecurity.configuration.WebSecurityConfigurer]
 */
@Service
class AuthenticateTokenIssuerService(
        @Autowired val authenticationManager: AuthenticationManager,
        @Autowired val jwtService: JwtService,
        @Autowired val authUserDetailService: AuthUserDetailService
): AuthTokenIssuer {

    @Throws(ResponseStatusException::class)
    override fun authenticate(request: AuthRequest): AuthResponse {
        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(request.username, request.password))
        } catch (e: BadCredentialsException){
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }

        val token = jwtService.issueTokenFor(authUserDetailService.loadUserByUsername(request.username))
        return AuthResponse(token)
    }
}

