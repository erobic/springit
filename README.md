# springit

[![Build Status](https://travis-ci.org/erobic/springit.svg?branch=master)](https://travis-ci.org/erobic/springit)

This project demonstrates commonly used features of Spring using best practices. You can use this project as a boilerplate for your own Spring Boot applications! 

The following topics are covered in this project:

1. RESTful API:
 1. Jackson: Field level configuration, Handling date/time
 2. AOP based exception handling

2. Transaction Management
3. Security
4. Caching
5. Scheduling
6. Asynchronous Calls
7. Profiles (for Environment specific configurations) 
8. Testing: Demonstrates testing [improvements](https://spring.io/blog/2016/04/15/testing-improvements-in-spring-boot-1-4) in of Spring Boot 1.4
  
  a. Unit Testing
  
  b. Integration Testing
  
  c. Test Coverage
Uses [Jacoco plugin](https://docs.gradle.org/current/userguide/jacoco_plugin.html) to generate test coverage reports, which helps us ensure that our tests cover our source properly.

9. Checkstyle:
Uses [checkstyle plugin](https://docs.gradle.org/current/userguide/checkstyle_plugin.html) to verify that source code style adheres to a pre-defined ruleset. This enables entire team to produce code with same style.

10. Static Code Analysis:
Uses [PMD plugin](https://docs.gradle.org/current/userguide/pmd_plugin.html) to analyze the source code and ensure that it adheres to best practices/design patterns. This is vital to make sure that the quality of code written adheres to highest possible standards.

