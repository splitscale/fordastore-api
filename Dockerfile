FROM openjdk:17

WORKDIR /app

COPY ./target/api-0.0.1-SNAPSHOT.jar /app

COPY ./src/main/resources/prod/ /app

EXPOSE 8081

CMD ["java", "-jar", "api-0.0.1-SNAPSHOT.jar"]