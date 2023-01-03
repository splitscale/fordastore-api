FROM openjdk:17

WORKDIR /fordastore/prod

COPY ./target/api-0.0.1.jar /fordastore/prod

EXPOSE 21119

CMD ["java", "-jar", "api-0.0.1.jar"]