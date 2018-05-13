Building a simple Git Server using Spring Boot & Jgit library

Steps to use:
--
Update properties in

``
src/resources/git-server.properties
``

Compile using:

``
mvn clean compile package
``

Run using:

``
java -jar target/git-server-*.jar
``

By default git-server is hosted on port 8080,
web api for viewing files is hosted on port 8090

Clone Repo:
--
Once application starts, you can clone the default repo
created with a default README.md file using your repo name.

``
git clone http://localhost:8080/repoName
``

Functionality:
--
1. Host a git repo on your (local)server
2. Capability to view repo over http
3. Create, Clone, Push changes to the repo


TODOs:
--
1. Git Push
2. Support Multiple repositories
3. UI for viewing repository files
