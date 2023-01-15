FROM openjdk:19-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 9903
ENTRYPOINT ["java","-jar","/app.jar"]