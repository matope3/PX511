# RUN BDD UI TESTS

## To run all Cucumber BDD tests, execute : 

./gradlew connectedCheck -Pcucumber

## To run specific Cucumber tests with tags, execute :

./gradlew connectedCheck -Pcucumber -Ptags="@e2e" 

#### General test tags :

- @e2e : run all end-to-end cucumber tests
- @smoke : run all smoke BDD tests 

#### Tags to test application against specific vulnerabilities :

- @sqli : tests against SQL Injection

#### Tags to run all tests available for given activity :

- @login : run security tests for a basic login activity

## To run individual feature test, execute : 

./gradlew connectedCheck -Pcucumber -Pscenario="Successful login"

#### Available Scenarios :

- Deny login for SQL injection