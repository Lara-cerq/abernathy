# Mediscreen

### Built With:
Java 1.8
Spring Boot
Maven
Thymeleaf
Bootstrap

### Installation

#### Clone the repo
git clone https://github.com/Lara-cerq/abernathy

#### Add in microservice patient application.properties your username and password:
spring.datasource.username=root

spring.datasource.password=root

#### Create docker networks :

```
docker network create mysql
docker network create mongo
```

#### Launch containers :

Go in each folder and run :

```
docker-compose up -d
```

### Usage
The application can be accessed via the following URL:

http://localhost:8082/patients -> Welcome page with list of all patients, access to patient history and access to diabetes report for each patient.

