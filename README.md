# SpringBoot, Integration Testing and Jwt Auth

### Backend

- Spring Boot
    - [Configuration Test AssertJ](src/test/kotlin/com/aiqency/springbootdemo/ConfigurationTest.kt)
    - [@LookUp Annotation](src/test/kotlin/com/aiqency/springbootdemo/LookUpTest.kt)
    - [Unsecured Endpoint Testing with @MockMvc & @MockBean](src/test/kotlin/com/aiqency/springbootdemo/MockMvc.kt)
    - [Test Spring Boot Profile](src/test/kotlin/com/aiqency/springbootdemo/ProfileTest.kt)
- Spring Data
    - [JPA Integration Test with Docker](src/test/kotlin/com/aiqency/springbootdemo/testcontainer/TcIntegrationTest.kt)
- Spring Security
    - [Jwt Auth](src/main/kotlin/com/aiqency/springbootdemo/springsecurity)
         - [Test Auth Token](src/test/kotlin/com/aiqency/springbootdemo/security/JwtAuthTest.kt)
         - [Test Secured Endpoint](src/test/kotlin/com/aiqency/springbootdemo/security/JwtSecuredEndPoint.kt)

### Tests
- [Test Containers](https://www.testcontainers.org/) (require docker)
- [Mockito](https://site.mockito.org/) 
- [JUnit4 & 5](https://junit.org/junit5/) 