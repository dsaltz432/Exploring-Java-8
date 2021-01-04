
#
# Build stage
#
FROM maven:3.5-jdk-8 AS buildStage
COPY src /home/src
COPY pom.xml /home
RUN mvn -f /home/pom.xml clean package

#
# Runtime stage - this simply runs one of the Java files
#
FROM gcr.io/distroless/java
COPY --from=buildStage /home/target/ExploringJava8-1.0.0-SNAPSHOT.jar /home/ExploringJava8-1.0.0-SNAPSHOT.jar
ENTRYPOINT ["java","-cp","/home/ExploringJava8-1.0.0-SNAPSHOT.jar","TestStreams"]
