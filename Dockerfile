FROM openjdk:8

EXPOSE 8082

ADD target/*.jar mediscreen-notes-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/mediscreen-notes-0.0.1-SNAPSHOT.jar"]