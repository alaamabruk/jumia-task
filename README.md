# Jumia-task

This repo contains jumia's phone service implementation, both front-end and back-end.
This project requires the implementation of a basic business logic related to the validation of international phone numbers.


Technologies & Frameworks used for development

Front End

Angular CLI: 13.1.4
Node.js version v17.4.0

Back End

JDK: 11
Gradle
Spring boot: 2.5.5
SQLite: 3.16.1

Test Frameworks

JUnit
Mockito
MockMvc


Task requirements

It was required to create a single-page web app that displays a a list of customers with their phone number and country and phone number validity.Phone number validity is checked against a rule (regex) for each country.

Implement Filtering by phone number validity, country, or both.
Add unit test for controller and services.
Docorize the project

Available data

A SqlLite database file with one table 'customer' and 2 columns 'name' and 'phone number'.
A rule for each country and country code to be used to check phone number validity.


Aditional frameworks used to enhance coding style and cleanliness
swagger API
Lombok (for getter & setter generation).

How to run the project

###### to build an image of back-end run the below command in the main repository directory :

> docker build  -t jumia-task .

###### then run the below to start a container:

> docker run -p 8080:8080 jumia-task


###### to build an image of front-end , navigate to front-end directory then run the below command:

> docker build -t front-end .

###### then run the below to start a container:

> docker run -p 3000:3000 front-end

Unit tests

Controller unit tests

Run PhoneControllerV1Tests.java

Service unit tests

Run PhoneServiceImplTest.java
Run PhoneValidityCheckerTest

