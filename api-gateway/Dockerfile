FROM openjdk:17
ARG JAR_PATH=target/*.jar
COPY ${JAR_PATH} getway.jar
ENTRYPOINT ["java", "-jar", "/getway.jar"]
EXPOSE 8080