FROM openjdk:19
COPY target/marktplaats-0.0.1-SNAPSHOT.jar marktplaats.jar
ENTRYPOINT ["java","-jar","/marktplaats.jar"]