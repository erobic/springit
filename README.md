# springit

[![Build Status](https://travis-ci.org/erobic/springit.svg?branch=master)](https://travis-ci.org/erobic/springit)

This project demonstrates commonly used features of Spring using best practices. You can use this project as a boilerplate for your own Spring Boot applications! 

The following topics are covered in this project:

**1. RESTful API:**
 
 *a. Jackson:* Field level configuration, Handling date/time
 
 *b. AOP:* For exception handling, security

**2. Transaction Management:** Rollback on exception

**3. Security:** Users, Permissions, Roles backed by database

**4. Caching:** For superfast retrieval

**5. Scheduling:** Cron/Fixed Delay/Fixed Rate schedulers to manage your jobs

**6. Asynchronous Calls:** To run long-running tasks in a separate thread and handle their results asynchronously

**7. Profiles:** Separate profiles from development, production and test

**8. Testing:** 
Following best practices as mentioned in [testing improvements in spring-boot-1-4](https://spring.io/blog/2016/04/15/testing-improvements-in-spring-boot-1-4)
  
*a. Unit Testing:* For controllers, services and utils
  
*b. Integration Testing:* For API endpoints, services and repositories
  
*c. Test Coverage:*
Uses [Jacoco plugin](https://docs.gradle.org/current/userguide/jacoco_plugin.html) to generate test coverage reports, which helps us ensure that our tests cover our source properly.

**9. Checkstyle:**
Uses [checkstyle plugin](https://docs.gradle.org/current/userguide/checkstyle_plugin.html) to verify that source code style adheres to a pre-defined ruleset. This enables entire team to produce code with same style.

**10. Static Code Analysis:**
Uses [PMD plugin](https://docs.gradle.org/current/userguide/pmd_plugin.html) to analyze the source code and ensure that it adheres to best practices/design patterns. This is vital to make sure that the quality of code written adheres to highest possible standards.
