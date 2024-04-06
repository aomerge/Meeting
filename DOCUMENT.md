# Meeting
thi is the API for meeting where you can create a meeting and invite people to join the meeting.

## Requirements
- docker
- docker-compose
- Java 17
- Maven
- Node 20

### Installation Docker
- Clone the project.
- Run the command `docker-compose up` to start the database.

### Instalation Local
- Clone the project.
- Configure the application.properties file with your database settings.
- Run Project in Intellij IDEA.
``` Bash
    Git clone https://github.com/aomerge/Meeting.git    
```
## Projeto Estructure
- [API Documentation](./StructureProject.md)


## Use Case
- [API Documentation](./UseCase.md)

## API Routes

* GET / login
* POST / register
* DELETE /user/delete
* Update /update
* POST /session/createSession
* UPDATE /session/updateSession/{id}
* DELETE /session/deleteSession/{id}
* POST /session/{id}/register
* POST /session/{id}/register/private
* DELETE /session/{id}/register/delete
* GET /session
* GET /session/{id}

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

### POST /register



## Licencia

Este proyecto está licenciado bajo la Licencia XYZ - ver el archivo LICENSE.md para más detalles.

## Agradecimientos

