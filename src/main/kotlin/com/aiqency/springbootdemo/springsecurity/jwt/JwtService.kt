package com.aiqency.springbootdemo.springsecurity.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService {

    val secret = "secret"

    fun usernameFromToken(token: String) = extractAllClaims(token).subject

    fun extractAllClaims(token: String): Claims =
        Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .body

    fun issueTokenFor(userDetail: UserDetails, claims: MutableMap<String, Any> = mutableMapOf()) =
            issueToken(userDetail.username, claims)

    private fun issueToken(subject: String, claims: MutableMap<String, Any> = mutableMapOf()) =
            Jwts.builder()
                    .setClaims(claims)
                    .setSubject(subject)
                    .setExpiration(Date(System.currentTimeMillis() * 1000 * 60 * 60 * 10))
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact()

    private fun isTokenExpired(token: String) = extractAllClaims(token).expiration.before(Date())

    fun validateToken(token: String, userDetail: UserDetails): Boolean =
            usernameFromToken(token) == userDetail.username && !isTokenExpired(token)

}