package com.aiqency.springbootdemo.springsecurity

import com.aiqency.springbootdemo.springsecurity.contracts.UserDetailsProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class AuthConf {

    private val encodedPass = "\$2a\$10\$bKt7pVdWaGNokcD65FRaDuIqBdNRWY0o3Z1cbEgMFKoFMVxhmtrF6"

    @Bean
    fun userDetails(): UserDetailsProvider {
        return object: UserDetailsProvider {
            override fun fromName(name: String?): UserDetails {
                //TODO fetch the user from the datasource
                return User("foo", encodedPass, arrayListOf())
            }
        }
    }

    @Bean
    fun passwordEncoder() : PasswordEncoder = BCryptPasswordEncoder()
}

