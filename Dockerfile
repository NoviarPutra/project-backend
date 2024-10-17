FROM openjdk:17-alpine
WORKDIR /app
COPY target/bootcamp-project-0.0.1-SNAPSHOT.jar sample-app.jar
ENTRYPOINT ["java", "-jar", "sample-app.jar"]