# define base docker image
FROM openjdk:17
LABEL maintainer="core-api"
ADD build/libs/core-0.0.1-SNAPSHOT.jar core-api.jar
ENTRYPOINT ["java", "-jar", "core-api.jar"]