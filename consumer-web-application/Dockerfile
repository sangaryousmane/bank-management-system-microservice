FROM openjdk:17

ARG JAR_PATH=target/*.jar

COPY ${JAR_PATH} consumer-web-app.jar
ENTRYPOINT ["java", "-jar", "/consumer-web-app.jar"]
EXPOSE  6464