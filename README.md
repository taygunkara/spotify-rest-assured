## spotify-rest-assured

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
- [ ] lombok
- [ ] allure reporting
- [ ] maven command
- [x] java faker
- [x] java enum for status codes
- [ ] parallel execution
- [ ] Cl integration

</details>