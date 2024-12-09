FROM openjdk:17-jdk-slim


WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

COPY src/main/resources/application.properties /app/src/main/resources/


RUN ./mvnw dependency:resolve

COPY src ./src

EXPOSE 8080

# Use spring-boot:run for hot reloading
CMD ["./mvnw", "spring-boot:run"]
