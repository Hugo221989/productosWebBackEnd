FROM openjdk:8

ADD target/restfull-0.0.1-SNAPSHOT.war restfull.jar

EXPOSE 8182

ENTRYPOINT ["java", "-jar", "restfull.jar"]
