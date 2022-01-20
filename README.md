# Patient Service System- Orthodontic Office

Project for JAVA Programming in the winter semester 2021/2022, simulating a patient service system in an orthodontic office

![Patient Service System - Orthodontic Office](https://github.com/Adam1904/SystemObslugiPacjenta-GabinetOrtodontyczny/blob/main/resources/icon.JPG)

## Setup

Before the first run you need to make sure that all .jar files are included in the project. You'll find them in the resources folder (in your `./resources`)

## How to Run

```sh
> cd aplikacja/src/
> javac *.java
> java PatientServiceSystem
```

## Dependiencies 

The application uses three .jar files, which are successively uploaded to the API into the environment:
- `itextpdf-5.4.0.jar` - iText is a library for creating and manipulating PDF files in Java 
- `javax.mail.jar` - The JavaMail API provides a platform-independent and protocol-independent framework to build mail and messaging applications. The JavaMail API is available as an optional package for use with the Java SE platform and is also included in the Java EE platform.
- `mysql-connector-java-8.0.22.jar` - MySQL Connector is the official JDBC driver for MySQL. This file enables communication between the database located in the cloud

## Design

The design is mainly based on two classes `GUI.java` and `DataBaseHandling.java` which are responsible for event-driven reactions to user commands. On the other hand we have classes like `MailHandling.java`, `PDFGenerator.java`, which relate to a strictly defined and specific matter related to their name. Also patient service system provides two classes `Visit.java`,`User.java`, which they are responsible for simulating a real user and the patient's visit itself with thier private fields.

## About

The project is based on an interactive event-driven response and provides the user with a large number of possible office management and patient service functions.
The system uses a database located in a cloud provided by GoogleCloud Platform, which uses the smallest possible computing power for testing purposes.
The program is designed to provide interactive responses to the situations that the user wants to perform and tries to handle personal data in the most secure way possible.

## Things to improve in the future

- aesthetic gui
- network communication
