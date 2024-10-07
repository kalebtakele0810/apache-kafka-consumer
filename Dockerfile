FROM maven:3.8.6-openjdk-18 AS build
LABEL maintainer="Kaleb.Takele"
WORKDIR /app
COPY pom.xml /app/
RUN mvn dependency:go-offline
COPY . /app
RUN mvn package -DskipTests
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/kafka-consumer-service-*.jar /app/kafka-consumer-service.jar

ENV TZ=Africa/Nairobi

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

CMD ["java", "-jar", "/app/kafka-consumer-service.jar"]
