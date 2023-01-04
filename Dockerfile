FROM openjdk:17

WORKDIR /fordastore/dev

COPY ./target/api-0.0.1-SNAPSHOT.jar /fordastore/dev

EXPOSE 8080

CMD ["java", "-jar", "api-0.0.1-SNAPSHOT.jar"]