# SpringBoot, Integration Testing and Jwt Auth

The app require a postgres instance running in localhost that can be configured it in the [application.yml](src/main/resources/application.yml)
### Backend

- Spring Boot
    - Source code
        - [Spring Security (Jwt Auth)](src/main/kotlin/com/aiqency/springbootdemo/springsecurity)
            - [Auth Endpoint](src/main/kotlin/com/aiqency/springbootdemo/springsecurity/rest/AuthRest.kt)
                - ```json
                  {
                      "username": "foo", 
                      "password": "foo"
                  }
                  ```
            - [Secured Endpoint](src/main/kotlin/com/aiqency/springbootdemo/springsecurity/rest/SecuredRest.kt)
                ```Authorization : Bearer <Token provided by the auth endpoint>```
        - [Spring Data (JPA)](src/main/kotlin/com/aiqency/springbootdemo/springdata)
        - [Rest Endpoint with Injected Interface DI](src/main/kotlin/com/aiqency/springbootdemo/rest/Rest.kt)
    - Tests
        - [Test Containers JPA Integration Test with Docker](src/test/kotlin/com/aiqency/springbootdemo/testcontainer/TcIntegrationTest.kt)
        - [Test Auth And Secure Endpoint](src/test/kotlin/com/aiqency/springbootdemo/security/JwtAuthAndSecureEndPoint.kt)
        - [Unsecured Endpoint Testing with @MockMvc & @MockBean](src/test/kotlin/com/aiqency/springbootdemo/MockMvc.kt)
        - [Test Spring Boot Profile](src/test/kotlin/com/aiqency/springbootdemo/ProfileTest.kt)
        - [Configuration Test AssertJ](src/test/kotlin/com/aiqency/springbootdemo/ConfigurationTest.kt)
        - [@LookUp Annotation](src/test/kotlin/com/aiqency/springbootdemo/LookUpTest.kt)