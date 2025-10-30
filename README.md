# MEW INDUSTRIES API

## 1) Requirements

- **Java:** OpenJDK **17.0.16** (`java -version`)  
- **Gradle:** Wrapper included (`./gradlew …`)  
- **Database:** Use the group DB repo for schema/setup & connection details:  
  [WSU-kduncan/cs4900-m-e-w-industries › DatabaseDesign/README.md](https://github.com/WSU-kduncan/cs4900-m-e-w-industries/blob/main/DatabaseDesign/README.md)  
- **Docker:** Running the database container  
- **Bruno:** For API testing

---

## 2) Project Layout

```text
cs4900-api-m-e-w-industries
├─ demo/                       # Spring Boot app (Gradle project)
│  ├─ bruno/                   # Bruno collection & environments
│  │  ├─ collection.bru
│  │  ├─ environments/
│  │  ├─ bruno.json
│  │  ├─ game/
│  │  ├─ matcheduser/
│  │  └─ user/
│  ├─ build.gradle
│  ├─ gradlew, gradlew.bat, gradle/
│  └─ src/
│     └─ main/
│        ├─ java/com/mew/demo/...
│        │  GamerMatchApplication.java
│        └─ resources/
├─ homework/
├─ MEWdb/
├─ SQL/
└─ README.md

```

## 3) Database

Follow the **group DB repo** for schema and setup (Docker Compose, seed data, ports, credentials, etc.):  
[DatabaseDesign/README.md](https://github.com/WSU-kduncan/cs4900-m-e-w-industries/blob/main/DatabaseDesign/README.md)

1. **Start the database container** from the `MEWdb/` directory (where `docker-compose.yml` lives) run:
```
docker-compose up
```

2. **Verify the connection** (e.g., in DBeaver) before starting the API.

## 4) Start the Service

From the `demo/` folder (where `build.gradle` lives):

```bash
./gradlew bootRun
```
## 5) Testing with Bruno

**Collection path:** `cs4900-api-m-e-w-industries/demo/bruno`

**Steps**
1. Open Bruno and **Open Collection** → select the `demo/bruno` folder.  
2. Choose **Environment**: `local` (ensure `BASE_URL` matches your running service).  
3. Run requests in folders: **user**, **game**, **matcheduser**.  
4. Expect **HTTP 200** for basic GETs and **201** for valid POST/PUT.

## 6) API Endpoints

**{{BASE_URL}}** `http://localhost:8080/GamerMatch`


### GET
- `GET {{BASE_URL}}/games` — List all games.

![GETAllGames](./homework/homework-5/GetAllGames.PNG)
  
- `GET {{BASE_URL}}/games/id/:id` — Get a single game by numeric `:id`.

![GETGameById](./homework/homework-5/GetGameById.PNG)
 
- `GET {{BASE_URL}}/games/title/:title` — Find games by `:title`.

![GETGameByTitle](./homework/homework-5/GetGameByTitle.PNG)

- `GET {{BASE_URL}}/matches/user/id/:id` — Get all matches for user `:id`.

![GETAllMatchedUsers](./homework/homework-5/GetAllMatchedUsers.PNG)

- `GET {{BASE_URL}}/matches/:userId/:matchId` — Get the match record for `:userId` vs `:matchId`.

![GETMatchInfo](./homework/homework-5/GetMatchInfo.PNG)

- `GET {{BASE_URL}}/users` — List all users.

![GETAllUsers](./homework/homework-5/GetAllUsers.PNG)

- `GET {{BASE_URL}}/users/id/:id` — Get a single user by numeric `:id`.

![GETUserById](./homework/homework-5/GetUserById.PNG)

- `GET {{BASE_URL}}/users/name/:firstName` — Search users by `:firstName`.

![GETUserByFirstName](./homework/homework-5/GetUserByFirstName.PNG)

### PATCH
- `PATCH {{BASE_URL}}/matches/:userId/:matchId` — Update an existing match record

![PATCHUpdateMatch](./homework/homework-5/UpdateMatch.PNG)

### POST

- `POST {{BASE_URL}}/users` — Create a new user. (**Body:** JSON user object.)

![POSTNewUser](./homework/homework-5/CreateNewUser.PNG)

- `POST {{BASE_URL}}/games` - Create/add a game (**Body:** JSON user object.)

![POSTCreateNewGame](./homework/homework-5/CreateNewGame.PNG)

### PUT
- `PUT {{BASE_URL}}/users/id/:id` — Replace or update a user by `:id`.  (**Body:** JSON user object.)

![PUTUser](./homework/homework-5/UpdateUser.PNG)

### DELETE
- `DELETE {{BASE_URL}}/users/id/:id` — Delete a user by `:id`.

![DELETEUser](./homework/homework-5/DeleteUser.PNG)

**Notes**
- Replace `:id`, `:userId`, `:matchId`, and `:firstName` with actual values.  
- Request/response shapes follow your DTOs/entities—use the Bruno collection in `demo/bruno` for exact fields and sample payloads.
