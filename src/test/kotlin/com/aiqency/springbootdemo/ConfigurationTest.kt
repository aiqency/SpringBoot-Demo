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
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

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


@RunWith(SpringRunner::class)
@ContextConfiguration(classes = [TestConfig::class])
class ConfigurationTest1 {

    @get:Rule
    val output = OutputCaptureRule() // STDOUT

    @Autowired
    lateinit var a: A

    @Test
    fun test(){
        a.print()
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