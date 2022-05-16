<h1 align="center">Automating Student Enrollment API rest-assured library, Cucumber Java </h1>
Framework makes use of RestAssured API for performing webservice operation and Cucumber framework for achieving BDD approach. 

## Can be Run Locally on Window/Linux/Mac
Prerequisite : Java ,Maven should be installed .

- git clone https://github.com/pawankalra2/Students-API-RestAssured-Cucumber.git or download Zip
- cd Students-API-RestAssured-Cucumber
- Run Java executable Jar by command "java -jar studentmgmt-0.0.1-SNAPSHOT.jar" to run API .(Pre requisite)
- mvn clean install 
The above command will download all dependencies and run all the tests based on cucumber tags present in runner->runCucumberTest (ex @SmokeTest)
- Set of test with similar feature tags can be run using the maven command "mvn test verify -Dcucumber.options="--tags @SmokeTest"

## Technology used:
- Java
- Rest-Assured
- Cucumber
- Maven
- cucumber-html-reports
- TestNG

## Folder structure
- "/src/test/java/features/"  : Cucumber feature file location 
Tests in feature file contains tags (eg: @SmokeTest,@E2E etc).
- "/src/test/java/stepDefinition/" : Step defination of cucumber fies for Get,Post,Put,Delete methods .
- "/src/test/java/runner/": TestNG runner class is used for running the test. 
- "src/test/java/data/studentDetails" : Java class is used to store Student Details.
- "src/test/java/util/commonFunctions" : Common Methods used for Get,Post,Put Delete .
- "config.properties" file contains the base urls and individual endPoint of the application. 
Any change to url is to be updated here. 
- POM.xml : It contains dependencies of project and has build tags for generating feature-view cucumber html reports .

## Scenarios automated
StudenTest.feature - Automated scenarios include complete END to END flow of Student App
- Ensure App is up and runnning before starting each scenario (Backgrond: )
- Adding a Student (POST method).
- Fetching Student DetaIls using id and studentClass (GET method).
- Updating Student details(PUT method).
- Deleting Student with id (DELETE METHOD).
- Validating all api calls return 200 except for negative scenarios.
- Some Negative scenarios to validate 400/500 response.

Below find cucumber-html-report of the test Run
Also present in "cucumber-html-reports.zip"

![image](https://user-images.githubusercontent.com/44734956/168651337-a91c70ba-512f-41f7-bbed-c176fbe57182.png)

![image](https://user-images.githubusercontent.com/44734956/168651245-20c40b5a-493e-47c2-a3b9-086e9ea0393b.png)

![image](https://user-images.githubusercontent.com/44734956/168651499-8b28622e-41dd-4a66-976a-0eb386e023c5.png)


