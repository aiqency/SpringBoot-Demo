package com.aiqency.springbootdemo.springsecurity.configuration

import com.aiqency.springbootdemo.springsecurity.jwt.JwtRequestFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
class WebSecurityConfigurer : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var authUserDetailService: AuthUserDetailService

    @Autowired
    lateinit var jwtFilter: JwtRequestFilter

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(authUserDetailService)
    }

    override fun configure(http: HttpSecurity?) {
        http?.csrf()?.disable()
                ?.authorizeRequests()
                ?.antMatchers("/secured/**")
                ?.authenticated()
                ?.anyRequest()
                ?.permitAll()
                ?.and()
                ?.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
                ?.sessionManagement()
                ?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Bean
    override fun authenticationManager(): AuthenticationManager {
        return super.authenticationManager()
    }

}
