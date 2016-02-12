# synqqTest
## About
synqqTest is a java web application, based on Spring boot, JPA/Hibernate + MySQL and Tomcat as a server. Front-end is powered by AngularJS and Bootsrtap. It's required Maven for building projects.

This application creates 10 threads of Writers and Readers which produce CRUD operations in MySQL server and allow to manage this proccess.
## Quick Start
### Installation
1. Import this project as a Maven project to your favorite IDE
2. Download and setup Apache Tomcat https://tomcat.apache.org/
3. Download MySQL https://www.mysql.com/downloads/
4. Tune /src/main/resources/application.properties to setup MySQL connection
5. Run as Java application

### Usage

* Run this application
* Go to http://localhost:8080/ (or accprding your tomcat settings)
* Setup generation settings
  * Events amount defines the maximum count of events (N)
  * Attendee max range settings up range 1..M
* Press Start to begin reading / writing processes and Stop to finish it

![alt text](https://img-fotki.yandex.ru/get/64827/11245482.4/0_a1999_128bddd1_X5L)

![alt text](https://img-fotki.yandex.ru/get/65661/11245482.4/0_a1998_98fa5b0d_XXL)


## Architecture
### Database
![alt text](https://img-fotki.yandex.ru/get/4005/11245482.4/0_a1996_5c8aad6e_X5L)

### Application
![alt text](https://img-fotki.yandex.ru/get/41207/11245482.4/0_a199a_72299dac_X5L)
