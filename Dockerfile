FROM openjdk:17

WORKDIR /fordastore/api

COPY ./target/api-0.0.1-SNAPSHOT.jar /fordastore/api

EXPOSE 8081

CMD ["java", "-jar", "api-0.0.1-SNAPSHOT.jar"]