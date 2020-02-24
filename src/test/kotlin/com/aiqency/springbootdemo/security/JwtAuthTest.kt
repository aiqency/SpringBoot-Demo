package com.aiqency.springbootdemo.security

import com.aiqency.springbootdemo.springsecurity.contracts.AuthRequest
import com.google.gson.Gson
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.json.JacksonJsonParser
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class JwtAuthTest {

    @LocalServerPort
    var port: Int = 0

    @Autowired
    lateinit var mockMvc: MockMvc

    val authUrl by lazy { "http://localhost:$port/auth" }

    val testUser = AuthRequest("foo", "foo")

    @Test
    fun `login as foo should return authToken`() {
        val response = mockMvc.perform(post(authUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(Gson().toJson(testUser)))
                .andReturn().response.contentAsString

        assertThat(JacksonJsonParser().parseMap(response)).containsKey("authToken")
    }

}