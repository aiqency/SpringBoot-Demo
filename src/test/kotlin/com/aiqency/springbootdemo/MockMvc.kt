package com.aiqency.springbootdemo

import com.aiqency.springbootdemo.rest.Greeting
import com.aiqency.springbootdemo.rest.Rest
import com.aiqency.springbootdemo.springsecurity.configuration.WebSecurityConfigurer
import org.hamcrest.Matchers.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.test.context.support.WithUserDetails
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status



@EnableWebSecurity
@ConditionalOnMissingBean(WebSecurityConfigurer::class)
class ByPassSecurityConf : WebSecurityConfigurerAdapter()


@RunWith(SpringRunner::class)
@WebMvcTest
@ContextConfiguration(classes = [ByPassSecurityConf::class, Rest::class])
@AutoConfigureMockMvc
@WithUserDetails
class MockRestTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var service: Greeting

    @Test
    fun `greeting endpoint`() {
        `when`(service.hello).thenReturn("Hi")
        mockMvc.perform(get("/greeting/world"))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(content().string(containsString("Hi World!")))
    }

}