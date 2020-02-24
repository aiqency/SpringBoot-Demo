package com.aiqency.springbootdemo.springsecurity.contracts

import org.springframework.security.core.userdetails.UserDetails

/**
 * Verify if the user is legit and issue his JWTtoken
 */
interface AuthTokenIssuer {
    fun authenticate(request: AuthRequest): AuthResponse
}

/**
 * The behaviour of the bean that should retrieve the UserDetails from any datasource
 */
interface UserDetailsProvider {
    fun fromName(name: String?): UserDetails
}

data class AuthRequest(var username: String, var password: String)
data class AuthResponse(var authToken: String)
