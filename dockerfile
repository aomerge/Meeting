# Usar una imagen base de Java
FROM openjdk:17-jdk-slim as build

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar los archivos del proyecto al contenedor
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

# Empaquetar la aplicación
RUN ./mvnw package -DskipTests

# Usar una imagen base ligera para el contenedor final
FROM openjdk:11-jre-slim

ARG JAR_FILE=/app/target/*.jar

# Copiar el JAR empaquetado al contenedor final
COPY --from=build ${JAR_FILE} app.jar

# Comando para ejecutar la aplicación
ENTRYPOINT ["java","-jar","/app.jar"]
