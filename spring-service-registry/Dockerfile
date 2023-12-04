FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} serviceregistry.jar
ENTRYPOINT ["java", "-jar", "/serviceregistry.jar"]
EXPOSE 8761


#
## First stage: build the application
#FROM openjdk:17 as builder
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} serviceregistry.jar
#RUN java -Djarmode=layertools -jar serviceregistry.jar extract
#
## Final stage: create a minimal image using distroless
#FROM gcr.io/distroless/java
#COPY --from=builder dependencies/ ./
#COPY --from=builder snapshot-dependencies/ ./
#COPY --from=builder spring-boot-loader/ ./
#COPY --from=builder application/ ./
#ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
#EXPOSE 8761
#
#
