FROM adoptopenjdk/openjdk11:alpine-jre

ADD build/libs/jumia-task-0.0.1-SNAPSHOT.jar jumia-task.jar
COPY sample.db sample.db
ENTRYPOINT ["java", "-jar","jumia-task.jar"]

EXPOSE 8080