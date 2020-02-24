package com.aiqency.springbootdemo.springsecurity.configuration

import com.aiqency.springbootdemo.springsecurity.contracts.UserDetailsProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

/**
 * This service is passed to the AuthenticationManagerBuilder class
 * in the WebSecurityConfigurerAdapter [WebSecurityConfigurer]
 */
@Service
class AuthUserDetailService : UserDetailsService {

    @Autowired
    lateinit var userProvider: UserDetailsProvider

    /**
     * Default spring method called to retrieve a UserEntity by name
     * this entity should implement the UserDetails interface
     */
    override fun loadUserByUsername(username: String?): UserDetails =
            userProvider.fromName(username)



}