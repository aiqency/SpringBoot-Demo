package com.aiqency.springbootdemo

import com.aiqency.springbootdemo.profile.ProfileInt
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment
import org.springframework.test.context.ActiveProfiles

/**
 * Change DI concrete type based on profiles
 */
@SpringBootTest
@ActiveProfiles("dev")
@AutoConfigureTestDatabase
class ProfileTest {

    @Autowired
    private lateinit var environment: Environment

    @Autowired
    private lateinit var profile: ProfileInt

    @Test
    fun profile() {
        assert(environment.activeProfiles.contains("dev"))
        assertEquals(profile.name, "dev")
    }

}