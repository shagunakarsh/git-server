Building a simple Git Server using Spring Boot & Jgit library

Steps to use:

Update properties in
src/resources/git-server.properties

Compile using:
mvn clean compile package

Run using:
java -jar target/git-server-*.jar

By default git-server is hosted on port 8080,
web api for viewing files is hosted on port 8090


Functionality:
1. Host a git repo on your (local)server
2. Capability to view repo over http
3. Create, Clone, Push changes to the repo
