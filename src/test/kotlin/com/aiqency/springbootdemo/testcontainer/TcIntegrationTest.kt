package com.aiqency.springbootdemo.testcontainer

import com.aiqency.springbootdemo.springdata.model.BookEntity
import com.aiqency.springbootdemo.springdata.repositories.ReadingListRepositoryInt
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
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

    val numOfEntitiesAdded = 3

    @Test
    fun `postgres container is running ?`(){
        assertTrue(postgresContainer.isRunning)
    }

    /**
     * Ensure database persistence and [ReadingListRepositoryInt.findByAuthor] fetching method
     */
    @Test
    fun readingListRepository(){
        assertThat(repo.findByAuthor("foo")).isEmpty()
        (0 until numOfEntitiesAdded).forEach { _ -> repo.save(BookEntity().apply { author = "foo" }) }
        assertThat(repo.findByAuthor("foo")).hasSize(numOfEntitiesAdded)
    }

}