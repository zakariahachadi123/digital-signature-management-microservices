# Dockerfile
FROM openjdk:17
EXPOSE 8081
ADD target/document-service.jar document-service.jar
ENTRYPOINT ["java","-jar","/document-service.jar"]