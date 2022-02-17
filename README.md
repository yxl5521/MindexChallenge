# Coding Challenge
## What's Provided
A simple [Spring Boot](https://projects.spring.io/spring-boot/) web application has been created and bootstrapped 
with data. The application contains information about all employees at a company. On application start-up, an in-memory 
Mongo database is bootstrapped with a serialized snapshot of the database. While the application runs, the data may be
accessed and mutated in the database without impacting the snapshot.
## What's Implemented
### Task 1
One endpoint created:
```
* READ
    * HTTP Method: GET 
    * URL: localhost:8080/structure/{id}
    * RESPONSE: ReportingStructure
```
Classes:
- ReportingStructure: The reporting structure entity
- ReportingStructureService/Impl: The logics for reading the reporting structure
- ReportingStructureController: Include the one endpoint described above  

ReportingStructure has a JSON scheme of:
```json
{
  "type":"ReportingStructure",
  "properties": {
    "employee": {
      "type": "employee"
    },
    "numberOfReports": {
      "type": "integer"
    }
  }
}
```
### Task 2
Two endpoints created:
```
* CREATE
    * HTTP Method: POST 
    * URL: localhost:8080/compensation
    * PAYLOAD: CompensationInput
    * RESPONSE: Compensation
* READ
    * HTTP Method: GET 
    * URL: localhost:8080/compensation/{id}
    * RESPONSE: Compensation
```
Classes:
- Compensation: The compensation data, save the fields needed
- CompensationInput: The input body for creating new compensation, only input employeeId without the need for the whole thing. 
- CompensationService/CompensationServiceImpl: The logic for saving and reading compensation
- CompensationController: Include the two endpoints described above
- CompensationRepository: Get the compensation from mongoDB using employee id  
```
Note: Currently design as one employee with one compensation. Need to know what to do when salary change.
- ```

Compensation has a JSON scheme of:
```json
{
  "type":"Compensation",
  "properties": {
    "employee": {
      "type": "employee"
    },
    "effectiveDate": {
      "type": "LocalDate"
    },
    "salary": {
      "type": "string"
    }
  }
}
```

CompensationInput has a JSON scheme of:
```json
{
  "type":"Compensation",
  "properties": {
    "employeeId": {
      "type": "string"
    },
    "effectiveDate": {
      "type": "string"
    },
    "salary": {
      "type": "string"
    }
  }
}
```
### How to Run
Build the application by running `gradle build`
The application may be executed by running `gradlew bootRun`.

### How to Use
The following endpoints are available to use:
```
* CREATE
    * HTTP Method: POST 
    * URL: localhost:8080/employee
    * PAYLOAD: Employee
    * RESPONSE: Employee
* READ
    * HTTP Method: GET 
    * URL: localhost:8080/employee/{id}
    * RESPONSE: Employee
* UPDATE
    * HTTP Method: PUT 
    * URL: localhost:8080/employee/{id}
    * PAYLOAD: Employee
    * RESPONSE: Employee
```
The Employee has a JSON schema of:
```json
{
  "type":"Employee",
  "properties": {
    "employeeId": {
      "type": "string"
    },
    "firstName": {
      "type": "string"
    },
    "lastName": {
          "type": "string"
    },
    "position": {
          "type": "string"
    },
    "department": {
          "type": "string"
    },
    "directReports": {
      "type": "array",
      "items" : "string"
    }
  }
}
```
For all endpoints that require an "id" in the URL, this is the "employeeId" field.

## What to Implement
Clone or download the repository, do not fork it.

### Task 1
Create a new type, ReportingStructure, that has two properties: employee and numberOfReports.

For the field "numberOfReports", this should equal the total number of reports under a given employee. The number of 
reports is determined to be the number of directReports for an employee and all of their distinct reports. For example, 
given the following employee structure:
```
                    John Lennon
                /               \
         Paul McCartney         Ringo Starr
                               /        \
                          Pete Best     George Harrison
```
The numberOfReports for employee John Lennon (employeeId: 16a596ae-edd3-4847-99fe-c4518e82c86f) would be equal to 4. 

This new type should have a new REST endpoint created for it. This new endpoint should accept an employeeId and return 
the fully filled out ReportingStructure for the specified employeeId. The values should be computed on the fly and will 
not be persisted.

### Task 2
Create a new type, Compensation. A Compensation has the following fields: employee, salary, and effectiveDate. Create 
two new Compensation REST endpoints. One to create and one to read by employeeId. These should persist and query the 
Compensation from the persistence layer.

## Delivery
Please upload your results to a publicly accessible Git repo. Free ones are provided by Github and Bitbucket.
