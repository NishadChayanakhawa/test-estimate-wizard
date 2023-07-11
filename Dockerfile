ARG JAVA_VERSION=17
FROM openjdk:${JAVA_VERSION}
COPY test-estimate-hub-webapp/target/*.jar test-estimate-hub-webapp.jar
EXPOSE 8999
CMD ["java","-jar","-Dspring.profiles.active=prod","/test-estimate-hub-webapp.jar"]
