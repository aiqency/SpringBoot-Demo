package com.aiqency.springbootdemo.security

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.security.test.context.support.WithUserDetails
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class JwtSecuredEndPoint {

    @LocalServerPort
    var port: Int = 0

    @Autowired
    lateinit var mockMvc: MockMvc

    val authUrl by lazy { "http://localhost:$port/secured/isAuth" }

    @Test
    @WithUserDetails
    fun `logged user should login`() {
        mockMvc.perform(get(authUrl))
                .andExpect(status().isOk)
                .andReturn()
    }

    @Test
    fun `without user details`() {
        mockMvc.perform(get(authUrl))
                .andExpect(status().isForbidden)
                .andReturn()
    }

}