FROM openjdk:17

WORKDIR /fordastore

COPY ./target/api-0.0.1-SNAPSHOT.jar /fordastore

COPY ./src/main/resources/ /fordastore

EXPOSE 8081

CMD ["java", "-jar", "api-0.0.1-SNAPSHOT.jar"]