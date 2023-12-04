FROM ubuntu:latest AS Build

RUN apt-get update
RUN apt-get install openjdk-18-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install -DskipTests

FROM openjdk:18-jdk

EXPOSE 8080

COPY --from=build /target/despesas-financas-backend-0.0.1-SNAPSHOT.jar app.jar


ENTRYPOINT [ "java", "-jar", "app.jar" ]