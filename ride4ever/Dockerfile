FROM maven:3.8.5-openjdk-17 AS builder
COPY ride4ever/src /usr/src/app/src
COPY schemas /usr/src/schemas
COPY ride4ever/pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:17.0.2-jdk-slim
COPY --from=builder /usr/src/app/target/ride4ever-1.0.2.jar /usr/app/ride4ever-1.0.2.jar

CMD ["java", "-cp", "/usr/app/ride4ever-1.0.2.jar", "com.github.zacharydhamilton.App"]