package com.aiqency.springbootdemo.testcontainer

import com.aiqency.springbootdemo.springdata.model.BookEntity
import com.aiqency.springbootdemo.springdata.repositories.ReadingListRepositoryInt
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.testcontainers.junit.jupiter.Testcontainers


@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
@Testcontainers(disabledWithoutDocker = true)
@ContextConfiguration(initializers = [BaseTc.Companion.Initializer::class])
class TcIntegrationTest: BaseTc() {

    @Autowired
    private lateinit var repo: ReadingListRepositoryInt

    @Test
    fun `postgres container is running ?`(){
        assertTrue(postgresContainer.isRunning)
    }

    @Test
    fun readingListRepository(){
        assertTrue(repo.findByAuthor("foo")?.isEmpty() == true)
        repo.save(BookEntity().apply { author = "foo" })
        repo.save(BookEntity().apply { author = "foo" })
        assertTrue(repo.findByAuthor("foo")?.size == 2)
    }

}