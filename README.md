Technologies/frameworks used:

- Spring framework - Inversion of Control (IoC) & Dependency Injection (DI)
- Testing frameworks - JUnit and Mockito
- Used Java 8 features

Design decision:
- Added Service layer for business logic and repository layer to reading data from    DB and stores back in DB in future with interfaces

Build tools:
- Mavenized project
- Added maven compiler plugin and dependencies


Input and Output:

Read data from "input.txt" file and arrange the list in lexicographical order and Write results to a "output.txt" file in human readable format.


Instructions to run the project in windows:

Clean project:

mvn clean 

Generate JAR file in target folder:

mvn install

To run test from command line:

mvn test



To run Jar file:

java -jar pizzaorders-0.0.1-SNAPSHOT-jar-with-dependencies.jar inputFileWithPath outputFileWithPath