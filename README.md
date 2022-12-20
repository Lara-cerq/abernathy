# Mediscreen

#### Built With:
Java 1.8
Spring Boot
Maven
Thymeleaf
Bootstrap

Usage
The application serves a Front End UI using Thymeleaf and Bootstrap, which can be accessed via the following URLs:

/ -> Home Page, welcome page with links to guide user through interface


### Installation
Clone the repo
git clone https://github.com/suracki/mediscreenapplication.git
Set configuration variables in each microservice's application.properties as desired Some properties of note: /Mediscreen/ -> Patient Demographic Service
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
