# Digital Signature Management Microservices

## Overview
This project implements a digital signature solution using microservices architecture with Spring Boot and Java 17.

This repository contains a microservices-based application including:
- API Gateway
- Auth Service (JWT Authentication and Authorization)
- Documents Service
- Signatures Service

## Prerequisites

Ensure you have the following installed:
- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Steps to Run the Application

### Step 1: Build Docker Images

Navigate to each microservice directory and build the Docker images:


docker build -t gateway-service-image:latest .
docker build -t auth-service-image:latest .
docker build -t document-service-image:latest .
docker build -t signature-service-image:latest .


### Step 2: Run Docker Compose
start the application using docker-compose.yml file

docker-compose up


### Step 3: Access the Services
Access the services at the following URLs:

  #### Regist an user ######
curl --location --request POST 'http://localhost:8080/auth/register' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=7CE91EE75A65277C0DCB6C5736C5DF5D' \
--data-raw '{
    "name":"hachadi",
    "password":"pass",
    "email":"zakaria@gmail.com"
}'

  #### login Generate token ######
curl --location --request POST 'http://localhost:8080/auth/login' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=7CE91EE75A65277C0DCB6C5736C5DF5D' \
--data-raw '{
    "username":"hachadi",
    "password":"Pass"
}'

 ##### Access  documents service  #######

 curl --location --request GET 'http://localhost:8080/documents/list' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJCYXNhbnQiLCJpYXQiOjE2NzkwNTU4MDIsImV4cCI6MTY3OTA1NzYwMn0.Q0bwS5_16q1Z8K-p_flpmyRoJNFCyOhU2AMKSNYh66o' \
--header 'Cookie: JSESSIONID=7CE91EE75A65277C0DCB6C5736C5DF5D'

##### Access  signature service ########
   curl --location --request GET 'http://localhost:8080/signatures/sign' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJCYXNhbnQiLCJpYXQiOjE2NzkwNTU4MDIsImV4cCI6MTY3OTA1NzYwMn0.Q0bwS5_16q1Z8K-p_flpmyRoJNFCyOhU2AMKSNYh66o' \
--header 'Cookie: JSESSIONID=7CE91EE75A65277C0DCB6C5736C5DF5D'
Body : 
  {
    "documentId":"988",
    "signature": "h48789prod2ct"

  }

##### Stopping the Services ########

docker-compose down
