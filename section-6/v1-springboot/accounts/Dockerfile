#Start with a base image containing Java runtime
FROM eclipse-temurin:21-jdk-alpine

#Add Maintainer Info
LABEL maintainer="John"

#Add the aplication jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#Execute the application
ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]