# Dockerfile
FROM openjdk:17
EXPOSE 8082
ADD target/signature-service.jar signature-service.jar
ENTRYPOINT ["java","-jar","signature-service.jar"]