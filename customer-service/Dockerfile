# Base image
FROM openjdk:17

VOLUME /temp
# Add a maintainer of this image
LABEL author="sangary7683@yahoo.com"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} customer-service.jar
ENTRYPOINT ["java", "-jar", "/customer-service.jar"]
EXPOSE 8083