# Dockerfile
FROM openjdk:17
EXPOSE 8083
ADD target/auth-service.jar auth-service.jar
ENTRYPOINT ["java","-jar","/auth-service.jar"]