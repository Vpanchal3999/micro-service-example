## Use a base image with the desired version of Java
#FROM openjdk:17-jdk-slim
#
## Set the working directory within the container
#WORKDIR /app
#
## Copy the Spring Boot application JAR to the container
#COPY build/libs/*.jar /app/app.jar
#
## Expose the port your application is listening on (if applicable)
#EXPOSE 9000
#
## Run the Spring Boot application when the container starts
#CMD ["java", "-jar", "app.jar"]

FROM openjdk:17-oracle
LABEL authors="vishesh"
COPY target/Payment-Service-docker.jar /Payment-Service-docker.jar
ENTRYPOINT ["java", "-jar","/Payment-Service-docker.jar"]