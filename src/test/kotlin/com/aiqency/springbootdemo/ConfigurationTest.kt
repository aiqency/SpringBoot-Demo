package com.aiqency.springbootdemo

import com.aiqency.springbootdemo.lookup.A
import com.aiqency.springbootdemo.lookup.B
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import org.springframework.boot.test.system.OutputCaptureRule
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

/**
 * Use ApplicationContextRunner to retrieve the application configuration
 */
class ConfigurationTest {

    @get:Rule
    val output = OutputCaptureRule() // STDOUT

    private val contextRunner = ApplicationContextRunner()
            .withUserConfiguration(TestConfig::class.java)

    @Test
    fun test(){
        contextRunner.run {context ->
            assertThat(context).hasSingleBean(A::class.java)
            context.getBean(A::class.java).print()
            assertThat(output.toString()).contains("Hello")
        }
    }

}


/**
 * Use the @ContextConfiguration annotation to retrieve the application configuration
 */
@RunWith(SpringRunner::class)
@ContextConfiguration(classes = [TestConfig::class])
class ConfigurationTest1 {

    @get:Rule
    val output = OutputCaptureRule() // STDOUT

    @Autowired
    lateinit var context: ApplicationContext

    @Autowired
    lateinit var a: A

    @Test
    fun test(){
        assertThat(context.getBean(A::class.java)).isNotNull()
        context.getBean(A::class.java).print()
        assertThat(output.toString()).contains("Hello")
    }
}

@Configuration
class TestConfig {

    @Bean
    fun a() = A()

    @Bean
    fun b() = B()

}