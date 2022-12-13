FROM openjdk:19-alpine

COPY target/rapportDiabete-0.0.1-SNAPSHOT.jar assessment-api-0.0.1.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/assessment-api-0.0.1.jar"]