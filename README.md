hbase-dao-test
==============

This is simple a Spring Boot application to demonstrate usage of a DAO with HBase. The project includes a simple web interface to drive basic tasks such as inserting and querying.

Prerequisites
-------------

### Maven ###

This project requires a recent version of Maven.

### HBase ###

You should have an instance of HBase available and running. You can install a standalone instance locally, use a distribution such as CDH, or point to any other instance of HBase.

Setup
-----

Update `src/main/resources/application.properties` with the connection properties of your HBase installation.

Run
---

To run the application, navigate to the project root and execute at the command line:

    mvn spring-boot:run

Usage
-----

Open a browser to <a href="http://localhost:8080">http://localhost:8080</a>.

TODO
----

* Write unit tests
* Fix the NPE when quitting the application.


