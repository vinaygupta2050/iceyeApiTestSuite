# API Test Automation Framework

This framework can  help you speed up your framework setup process as it consist of most of the feature required to test Rest Api.

## Getting Started

To get started clone project from github. 

Below are the api used to write this framework which will help you drive your test.
* TestNg
* RestAssured
* Json Schema Validator
* Json Path
* Gson
* Extent Report

Prerequisites

* JDK 1.8 or hire version should be installed.
* Maven should be installed.


The library which plays a major role in building this framework is Rest Assured.  REST Assured is a Java library that provides a domain-specific language (DSL) for writing powerful, maintainable tests for RESTful APIs.

Folder Structure:
```

├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── api
│   │   │           ├── apiFactory
│   │   │           ├── listener
│   │   │           ├── pojo
│   │   │           └── utils
│   │   └── resources
│   └── test
│       ├── testData
│       │   └── GreetApiTest
│       ├── java
│       │   └── com
│       │       └── api
│       │           └── test
│       └── resources
└── test-output
    └── Report
```

## Configuration

* Environment config files (*.properties) to target your service.
* JSON schemas which describe the payloads sent to the service

These two files should be placed in below directories
* [Environment config](https://github.com/vinaygupta2050/iceyeApiTestSuite/tree/master/src/main/resources)
* [JSON Schemas](https://github.com/vinaygupta2050/iceyeApiTestSuite/tree/master/src/test/resources)

### Target Environments
The framework is designed so that multiple environments along with their required vairables can be supported and multiple services for each environment can be defined with in folder [Environment config](https://github.com/vinaygupta2050/ApiTestAutomationFramework/tree/master/src/main/resources) 
```
serverUrl=http://localhost:3000
userName=admin
password=password123
```
### Target End Points
List end points can be written in class [ApiEndPoints](https://github.com/vinaygupta2050/iceyeApiTestSuite/blob/master/src/main/java/com/api/apiFactory/ApiEndPoints.java)  

### Headers
Required Headers can be defined in HashMaps can de declared in class [ApiHeaders](https://github.com/vinaygupta2050/iceyeApiTestSuite/blob/master/src/main/java/com/api/apiFactory/ApiHeaders.java)

### Payloads
The framework is written in such a way that user needs to create a POJO class for the respective JSON reqruest or payload with which we wanted to hit the end points the POJO. The POJO class should be written in folder [pojo](https://github.com/vinaygupta2050/ApiTestAutomationFramework/tree/master/src/main/java/com/api/pojo).

### Schema Validation
The framework is capable of validating JSON schema against the response received. To achieve that user needs to place the JSON Schema file in folder [resources](https://github.com/vinaygupta2050/ApiTestAutomationFramework/tree/master/src/test/resources).

### Maintaining Test Data
The framework is capable of maintaining test data for the respective test in below directory.
* [Test Data](https://github.com/vinaygupta2050/iceyeApiTestSuite/tree/master/src/test/testData)

## Writing Test

Once you have finished your configuration you can begin writing your test by extending [BaseTest](https://github.com/vinaygupta2050/iceyeApiTestSuite/blob/master/src/test/java/com/api/test/BaseTest.java) to your respective test. Below ist sample code.
```
    @Test(enabled = true,description = "Verify whether user is able to get greet message as per the name passed to api end point",dataProvider = "testData",dataProviderClass = CsvDataProvider.class)
    public void getGreetMessage(String name)
    {
        httpRequest.headers(ApiHeaders.defaultHeader());
        Response rs=httpRequest.request(Method.GET, "/"+name);
        ExtentReportManager.getTest().log(Status.INFO,"Status Code : "+rs.getStatusCode());
        ExtentReportManager.getTest().log(Status.INFO,"Response    : "+rs.getBody().prettyPrint().toString());
        Assert.assertTrue(rs.getBody().asString().contains("Vinaykumar"));
    }

```

## Running your Test
When you are ready to run your tests from the command line, below are a few examples of run commands (standard maven command line syntax):

* Right now we kept test environment as fixed. But in case if you want to pass test environment in maven command  you need to uncomment line number 16 in class [PropertyFileReader](https://github.com/vinaygupta2050/iceyeApiTestSuite/blob/master/src/main/java/com/api/utils/PropertyFileReader.java) . User below command to run your test
```
mvn clean test -Denv=QA
```
## Test Results

Once all the test are executed results can be generated in [test-output](https://github.com/vinaygupta2050/iceyeApiTestSuite/tree/master/test-output/Report)

![Dashboard](https://github.com/vinaygupta2050/iceyeApiTestSuite/blob/master/images/iceyeTestSuite.gif)

## Author

* **Vinaykumar Gupta** - [LinkedIn](https://in.linkedin.com/in/vinaygupta2050)
