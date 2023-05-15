## spotify-rest-assured

This is a project API Test Automation with Spotify API using RestAssured, TestNG and Allure Report.  

I have selected 3 positive, 2 negative test cases for Playlist. The variables are located in the `src/test/resources` folder. You should create a Spotify application for your own project and fill in the necessary information.
_Since the application is closed, the run without correcting the information will give an error._ You can see the steps I followed while creating the project and the project structure at the bottom.

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


#### Prerequisites

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

#### Usage
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
<details>
  <summary>To Do List</summary>

---

- [x] spec builder
- [x] positive playlist scenarios
  - [x] should be able to create a playlist
  - [x] should be able to get a playlist
  - [x] should be able to create a playlist
- [x] negative playlist scenarios
  - [x] should be able to create a playlist with name
  - [x] should be able to create a playlist with expired token
- [x] pojo
- [x] token manager
- [x] reusable methods
- [x] routes
- [x] config 
  - [x] property loader utility
  - [x] config loader - singleton design pattern
  - [x] data loader - singleton design pattern
- [ ] ~~lombok~~
- [x] allure reporting
- [x] java faker
- [x] java enum for status codes
- [ ] parallel execution
- [ ] Cl integration

</details>