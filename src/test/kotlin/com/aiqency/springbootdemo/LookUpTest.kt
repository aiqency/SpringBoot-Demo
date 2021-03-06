package com.aiqency.springbootdemo

import com.aiqency.springbootdemo.lookup.Prototype
import com.aiqency.springbootdemo.lookup.Singleton
import org.assertj.core.api.Assertions.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * Use of the @Lookup annotation
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [Singleton::class, Prototype::class])
@AutoConfigureTestDatabase
class LookUpTest {

	@Autowired
	lateinit var first: Singleton

	@Autowired
	lateinit var second: Singleton

	@Test
	fun lookUp() {
		assertThat(first).isEqualTo(second)
		assertThat(first.prototype).isNotEqualTo(second.prototype)
	}

}