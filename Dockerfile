FROM openjdk:8-jdk-alpine
COPY ./target/backend-1.0-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
COPY sample.db /usr/app/backend/sample.db
ENTRYPOINT ["java","-jar","backend-1.0-SNAPSHOT.jar"]