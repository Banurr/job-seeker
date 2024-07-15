FROM maven:3.8.4-openjdk-17
MAINTAINER banurr
WORKDIR /app
COPY pom.xml .
RUN mvn -B -Dmaven.plugin.skip=true dependency:go-offline
COPY . .
RUN mvn clean test
RUN mvn package
COPY target/pet-project-0.0.1-SNAPSHOT.jar /app/job-seeker.jar
ENTRYPOINT ["java","-jar","/app/job-seeker.jar"]