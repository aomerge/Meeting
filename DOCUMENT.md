# Meeting
thi is the API for meeting where you can create a meeting and invite people to join the meeting.

## Requirements
### docker
- docker
- docker-compose

#### Installation Docker
- Clone the project.
- Run the command `docker-compose up` to start the database.

### Local
- Java 17
- Maven
- Node 20
- Postgres
- Intellij IDEA 

#### Installation Local
- Clone the project.
``` Bash
    Git clone https://github.com/aomerge/Meeting.git    
```
- Configure the application.properties file with your database settings.
- Run Project in Intellij IDEA.

#### Data base configuration
- Create a database with the name `meeting`.
- You can configure the database in the file `application-dev.properties` in the folder `src/main/resources`.
- Change the values of the variables `spring.datasource.url`, `spring.datasource.username` and `spring.datasource.password` according to your database settings.

## Projeto Estructure
- [API Structure](./StructureProject.md)

## API Routes

The API has the following routes:

* [GET / login](#get-login)
* [POST / register](#post-register)
* [DELETE /user/delete](#delete-userdelete)
* [UPDATE /update](#update-userupdate)
* [POST /session/createSession](#post-sessioncreatesession)
* [UPDATE /session/updateSession/{id}](#update-sessionupdatesessionid)
* [DELETE /session/deleteSession/{id}](#delete-sessiondeletesessionid)
* [POST /session/{id}/register](#post-sessionidregister)
* [POST /session/{id}/register/private](#post-sessionidregisterprivate)
* [DELETE /session/{id}/register/delete](#delete-sessionidregisterdelete)
* [GET /session](#get-session)
* [GET /session/{id}](#get-sessionid)

### GET /login

- **Description:** This route is used to authenticate the user.
- **Parameters:** No parameters.
- **Request body:** 
  - `email`: User email.
  - `password`: User password.
- **Success response:** Structure of the response in case of success.
    - `status`: 200.
    - `message`: "User authenticated".
    - `data`: User data.
    - `token`: User token.

### POST /register
- **Description:** This route is used to register a new user.
- **Parameters:** No parameters.
- **Request body:** 
  - `name`: User name.
  - `email`: User email.
  - `password`: User password.
- **Success response:** Structure of the response in case of success.
    - `status`: 201.
    - `message`: "User registered".
    - `data`: User data.

### DELETE /user/delete
- **Description:** This route is used to delete a user.
- **Headers:** 
  - `Authorization`: User token.
- **Parameters:** 
  - `confirm`: Confirmation of deletion.
- **Request body:** 
  - `email`: User email.
  - `password`: User password.
- **Success response:** Structure of the response in case of success.
    - `status`: 200.
    - `message`: "User deleted".
    - `data`: User data.

### UPDATE /user/update
- **Description:** This route is used to update a user.
- **Headers:** 
  - `Authorization`: User token.
- **Parameters:** No parameters.
- **Request body:** 
  - `name`: User name.
  - `email`: User email.
  - `password`: User password.
- **Success response:** Structure of the response in case of success.
    - `status`: 200.
    - `message`: "User update".
    - `data`: User data.
  
### POST /session/createSession
- **Description:** This route is used to create a new session.
- **Headers:** 
  - `Authorization`: User token.
- **Parameters:** No parameters.
- **Request body:** 
  - `name`: Session name.
  - `date`: Session date.
  - `time`: Session time.
  - `description`: Session description.
- **Success response:** Structure of the response in case of success.
    - `status`: 201.
    - `message`: "Session created".
    - `data`: Session data.

### UPDATE /session/updateSession/{id}
- **Description:** This route is used to update a session.
- **Headers:** 
  - `Authorization`: User token.
- **Parameters:** No parameters.
- **Request body:** 
  - `name`: Session name.
  - `date`: Session date.
  - `time`: Session time.
  - `description`: Session description.
- **Success response:** Structure of the response in case of success.
    - `status`: 200.
    - `message`: "Session updated".
    - `data`: Session data.

### DELETE /session/deleteSession/{id}
- **Description:** This route is used to delete a session.
- **Headers:** 
  - `Authorization`: User token.
- **Parameters:** 
  - `confirm`: Confirmation of deletion.
- **Request body:** 
  - `id`: Session id.
- **Success response:** Structure of the response in case of success.
    - `status`: 200.
    - `message`: "Session deleted".
    - `data`: Session data.
  
### POST /session/{id}/register
- **Description:** This route is used to register a user in a session.
- **Headers:** 
  - `Authorization`: User token.
- **Parameters:** No parameters.
- **Request body:** 
  - `email`: User email.
- **Success response:** Structure of the response in case of success.
    - `status`: 201.
    - `message`: "User registered in session".
    - `data`: Session data.

### POST /session/{id}/register/private
- **Description:** This route is used to register a user in a private session.
- **Headers:** 
  - `Authorization`: User token.
- **Parameters:** 
  - `key`: Session key.
  - `email`: User email.
- **Request body:**: Not parameters.
- **Success response:** Structure of the response in case of success.
    - `status`: 201.
    - `message`: "User registered in private session".
    - `data`: Session data.

### DELETE /session/{id}/register/delete
- **Description:** This route is used to delete a user from a session.
- **Headers:** 
  - `Authorization`: User token.
- **Parameters:** No parameters.
- **Request body:** 
  - `email`: User email.
- **Success response:** Structure of the response in case of success.
    - `status`: 200.
    - `message`: "User deleted from session".
    - `data`: Session data.


### GET /session
- **Description:** This route is used to list all sessions.
- **Parameters:** No parameters.
- **Request body:** No parameters.
- **Success response:** Structure of the response in case of success.
    - `status`: 200.
    - `message`: "Sessions listed".
    - `data`: List of sessions.

### GET /session/{id}
- **Description:** This route is used to list a session.
- **Parameters:** No parameters.
- **Request body:** No parameters.

- **Success response:** Structure of the response in case of success.
    - `status`: 200.
    - `message`: "Session listed".
    - `data`: Session data.


## Authors
 - [aomerge](https://github.com/aomerge)

## Licencia

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

