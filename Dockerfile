#
# Build stage
#
FROM maven:3.9.0-eclipse-temurin-17-alpine AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM eclipse-temurin:17-jre-alpine
WORKDIR /search
COPY --from=build /home/app/target/simple-search.jar /simple-search.jar
ENTRYPOINT ["java","-jar","/simple-search.jar"]