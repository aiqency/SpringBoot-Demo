package com.aiqency.springbootdemo.springsecurity.jwt

import com.aiqency.springbootdemo.springsecurity.configuration.AuthUserDetailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class JwtRequestFilter: OncePerRequestFilter() {
    
    @Autowired
    lateinit var authUserDetailService : AuthUserDetailService

    @Autowired
    lateinit var jwtService : JwtService

    /**
     * We have to pass the Authentication object to the security context
     * @see [https://www.baeldung.com/manually-set-user-authentication-spring-security]
     */
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        request.takeIf { it.requestURI.contains("/secured") }
                ?.getHeader("Authorization")
                ?.takeIf { it.startsWith("Bearer") }
                ?.split("Bearer ")
                ?.filterNot { it.isEmpty() }
                ?.firstOrNull()
                ?.passAuthToTheSecurityContext(request)

        filterChain.doFilter(request, response)
    }

    private fun String.passAuthToTheSecurityContext(request: HttpServletRequest){
        jwtService.usernameFromToken(this)?.let { username ->
            val context = SecurityContextHolder.getContext()
            if (context.authentication == null){
                val userDetail = authUserDetailService.loadUserByUsername(username)
                if (jwtService.validateToken(this, userDetail)){
                    val authentication = UsernamePasswordAuthenticationToken(userDetail, null, userDetail.authorities)

                    authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                    context.authentication = authentication
                }
            }
        }
    }
}