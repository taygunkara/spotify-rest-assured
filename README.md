# Spotify API Test Automation Framework

This project provides a comprehensive API test automation framework for the Spotify API, utilizing Rest Assured, TestNG, and Allure for reporting. It's built on a layered architecture that promotes clean code and maintainability.

### Key Architectural Features
- **Layered Design:** Strict separation of concerns between API logic (`PlaylistApi`), Rest Assured abstraction (`RestResource`), and test scenarios (`PlaylistTests`).
- **Reusable Specifications:** Centralized request/response settings using a `SpecBuilder` to avoid code duplication.
- **POJO-Based Testing:** Uses Plain Old Java Objects (POJOs) for serialization and deserialization of request/response bodies.
- **Named Status Codes:** Employs a `StatusCode` class to replace magic numbers, improving test readability and maintainability.

### Future Enhancements: From Course Project to Professional Framework

This framework was initially built with assistance from a course, providing a solid foundation. The following enhancements are planned to evolve it into a fully robust and professional-grade automation solution:

1.  **Implement Atomic Test Scenarios:** Refactor all test cases to be fully independent and self-contained. Each test (e.g., "Get a Playlist") will programmatically create its own test data via a POST request before execution and, where applicable, clean it up afterward. This will eliminate dependencies on static data files and drastically improve test reliability.
2.  **Modernize the StatusCode Class:** Convert the current `StatusCode` POJO class into a more powerful and type-safe Java `enum`. This will ensure a fixed set of possible statuses and align with modern Java best practices.
3.  **Adhere to Single Responsibility Principle (SRP):** Split the `PlaylistHelper` class into two more focused classes: a `PlaylistDataFactory` for creating test data objects and a `PlaylistAssertions` class dedicated to validation logic.
4.  **Integrate Structured Logging:** Add **SLF4J** and a Logback/Log4j2 backend to provide detailed logging for every request and response, which is crucial for effective debugging in CI/CD environments.


<details>
  <summary>You should change the following in the "config.properties":</summary>

```
client_id=
client_secret=
refresh_token=
user_id=
```
</details>

<details>
  <summary>You should change the following in the "data.properties":</summary>

```
update_playlist_id=
get_playlist_id=
```

</details>

---


## Prerequisites

What you need to install on the system:
- Java
- Maven
- Allure

#### Used Maven Libraries

- javafaker
- allure-rest-assured
- allure-testng
- jsonassert
- jackson-databind
- testng
- rest-assured

## Usage
```
mvn clean test
```

#### Report
```
allure serve
```

---

<details>
  <summary>Project Structure</summary>

```
📦 spotify-api-test-rest-assured-architecture   
├─ .idea 
├─ allure-results  
├─ .gitignore  
├─ .pom.xml 
├─ README.md  
└─ src  
   ├─ main  
   ├─ java  
   └─ test  
      ├─ java  
      │  └─ com.spotify.oauth2  
      │        ├─ api 
      │        │   └─ applicationApi
      │        │      ├─ PlaylistApi.java
      │        │      └─ PlaylistHelper.java
      │        │   ├─ RestResource.java
      │        │   ├─ Route.java
      │        │   ├─ SpecBuilder.java
      │        │   ├─ StatusCode.java
      │        │   └─ TokenManager.java
      │        ├─ models  
      │        │   ├─ Error.java
      │        │   ├─ ExternalUrls.java
      │        │   ├─ ExternalUrls__1.java
      │        │   ├─ Followers.java
      │        │   ├─ InnerRoot.java
      │        │   ├─ Owner.java  
      │        │   ├─ Playlist.java 
      │        │   └─ Tracks.java 
      │        ├─ tests
      │        │   └─ PlaylistTests.java
      │        ├─ utils
      │        │   ├─ ConfigLoader.java
      │        │   ├─ DataLoader.java
      │        │   ├─ FakerUtils.java
      │        │   └─ PropertyUtils.java
      └─ resources  
         ├─ config.properties
         └─ data.properties    
```
</details>
