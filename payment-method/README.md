this project simply  demonostrate how to integrate esewa payment portal using spring boot and thymeleaf
It allows users to:
- Choose a payment method
- Redirect to eSewa login page
- Complete payment securely
Features

 eSewa ePay Integration (UAT/Test)  
 HMAC SHA256 Signature Generation  
Auto-submit payment form using Thymeleaf  
Success & Failure redirect handling  
 Simple UI for payment selection 

 Tech Stack

- Java 17+
- Spring Boot
- Thymeleaf
- Apache Commons Codec (for Base64)
- Maven

<dependencies>

    <!-- Spring Boot Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Thymeleaf Template Engine -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>

    <!-- Apache Commons Codec (Base64 encoding for signature) -->
    <dependency>
        <groupId>commons-codec</groupId>
        <artifactId>commons-codec</artifactId>
        <version>1.16.0</version>
    </dependency>

</dependencies>