# Dockerfile
# Dockerfile
FROM openjdk:17
EXPOSE 8080
ADD target/gateway-service.jar gateway-service.jar
ENTRYPOINT ["java","-jar","/gateway-service.jar"]