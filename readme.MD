### RESTful with Spring Boot - Quick start

# From command line 
How to run the application directly from the command line. 

```
$ cd <project_folder>
$ mvn install
```

The folder "target" contains the application in a .jar file. 
I just need to run this .jar file to start the embedded Tomcat with the application.

```
$ java -jar ./target/xxxx-xxxx-xxxx-0.0.1-SNAPSHOT.jar
```

To clean the target folder (remove target folder) and rebuild the project again. 

```
$ mvn clean
$ mvn install 
$ java -jar ./target/xxxx-xxxx-xxxx-0.0.1-SNAPSHOT.jar
```
