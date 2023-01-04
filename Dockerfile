FROM openjdk:17

WORKDIR /fordastore/api

COPY ./target/api-0.0.1-SNAPSHOT.jar /fordastore/api
COPY ./src/main/resources/store-db.properties /fordastore/api

EXPOSE 8081

CMD ["java", "-jar", "api-0.0.1-SNAPSHOT.jar"]