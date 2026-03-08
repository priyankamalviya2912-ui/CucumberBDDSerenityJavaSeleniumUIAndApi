![Last Commit](https://img.shields.io/github/last-commit/serenity-bdd/serenity-cucumber-starter) ![Java](https://img.shields.io/badge/Java-17-blue) ![Serenity](https://img.shields.io/badge/Serenity_BDD-5.0.4-brightgreen) ![Cucumber](https://img.shields.io/badge/Cucumber-7.33.0-green)

---

<div align="center">

# Serenity BDD + Cucumber + Selenium + RestAssured

## Hybrid UI and REST API Test Automation Framework

</div>

---

Welcome to the **Serenity BDD Cucumber Framework**. This hybrid automation framework combines [Serenity BDD](https://serenity-bdd.info/), [Cucumber](https://cucumber.io/), [Selenium WebDriver](https://www.selenium.dev/), and [RestAssured](https://rest-assured.io/) to deliver clean, readable, and maintainable UI and API tests in Java.

Tests are written in **Gherkin** and serve as living documentation that stays in sync with actual test results.

---

## Key Features

- **Hybrid UI & API Testing** — one framework for browser automation (Selenium) and REST API validation (RestAssured).
- **BDD with Cucumber** — tests written in plain Gherkin, readable by non-technical stakeholders.
- **Page Object Model** — UI page interactions are encapsulated in Serenity `PageObject` classes.
- **POJO Deserialization** — API responses are deserialized into typed Java POJOs using Jackson.
- **JSON-driven Test Data** — test inputs and POST payloads are managed in external JSON files.
- **Environment-aware Config** — all base URLs live in `serenity.conf`, never in code.
- **Serenity BDD Reporting** — rich single-page HTML report with step-level detail and failure screenshots.

---

## Table of Contents

- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [API Configuration](#api-configuration-serenityconf)
- [APIs Under Test](#apis-under-test)
  - [Open Library — GET](#1-open-library-api--get)
  - [SpaceX Latest Launch — GET](#2-spacex-latest-launch-api--get)
  - [Dummy REST API — Employee POST](#3-dummy-rest-api--employee-post)
- [BDD Feature File](#bdd-feature-file)
- [Step Definitions](#step-definitions)
- [Package Structure](#package-structure-detail)
- [Test Runners](#test-runners)
- [Test Data](#test-data)
- [Executing Tests](#executing-tests)
- [Reports](#reports)
- [Best Practices](#best-practices)

---

## Technology Stack

| Tool / Library         | Version        | Purpose                                    |
|------------------------|----------------|--------------------------------------------|
| Java                   | 17             | Programming language                       |
| Maven                  | 3.x            | Build and dependency management            |
| Serenity BDD           | 5.0.4          | Test orchestration and HTML reporting      |
| Cucumber               | 7.33.0         | BDD feature file parsing and execution     |
| Serenity REST Assured  | 5.0.4          | RestAssured wrapper with Serenity logging  |
| Selenium WebDriver     | via Serenity   | Browser UI automation                      |
| JUnit Platform         | 6.0.1          | Test runner platform (Suite API)           |
| Jackson Databind       | 2.17.0         | JSON serialization and POJO deserialization|
| AssertJ                | 3.23.1         | Fluent assertion library                   |
| Logback                | 1.2.10         | Logging                                    |

---

## Project Structure

```
CucumberBDDSerenityJavaSeleniumUIAndApi-main/
├── pom.xml
├── serenity.properties
└── src/
    └── test/
        ├── java/
        │   ├── api/                                  # API action classes (RestAssured calls)
        │   │   ├── AuthorApi.java                    # GET - Open Library authors endpoint
        │   │   ├── SpacexApi.java                    # GET - SpaceX latest launch endpoint
        │   │   ├── EmployeeApi.java                  # POST - Employee creation endpoint
        │   │   ├── steps/
        │   │   │   ├── AuthorSteps.java              # Cucumber step definitions for all API scenarios
        │   │   │   └── ApiStepDefinition.java        # (legacy - commented out)
        │   │   ├── runner/
        │   │   │   └── ApiCucumberTestSuite.java     # API test runner
        │   │   └── pojo/
        │   │       ├── Author.java                   # POJO - Open Library author response
        │   │       ├── Type.java                     # POJO - Author type sub-object
        │   │       └── spacexpojo/
        │   │           ├── Spacex.java               # POJO - SpaceX launch response
        │   │           ├── Core.java                 # POJO - SpaceX core data
        │   │           └── Links.java                # POJO - SpaceX launch links
        │   ├── ui/                                   # UI automation layer
        │   │   ├── pages/
        │   │   │   ├── StampDutyLandingPage.java     # Landing page object
        │   │   │   ├── MotorVehicleRegistrationPage.java # Calculator form page object
        │   │   │   └── CalculatorPopupPage.java      # Result popup page object
        │   │   ├── steps/
        │   │   │   └── StepDefinitions.java          # Cucumber step definitions for UI
        │   │   └── runner/
        │   │       └── CucumberTestSuite.java        # UI test runner
        │   └── utils/
        │       ├── JsonReader.java                   # Reads test data and payload JSON files
        │       └── HelperMethods.java                # Shared WebDriver utility methods
        └── resources/
            ├── serenity.conf                         # Environment config and base URLs
            ├── junit-platform.properties             # Parallel execution settings
            ├── logback-test.xml                      # Logging configuration
            ├── features/
            │   └── nsw/
            │       ├── api.author.feature            # API BDD scenarios (GET + POST)
            │       └── stamp_duty_check.feature      # UI BDD scenarios
            ├── testdata/
            │   ├── authorData.json                   # GET test data for Open Library
            │   └── spacex.json                       # SpaceX reference data
            └── payload/
                └── createEmployee.json               # POST request body for Employee API
```

---

## API Configuration (`serenity.conf`)

All base URLs are environment-driven and defined centrally in `src/test/resources/serenity.conf`. They are loaded at runtime via Serenity's `EnvironmentSpecificConfiguration` — no URLs are hard-coded in test code.

```hocon
serenity {
    take.screenshots = FOR_FAILURES
    report {
       accessibility = true
       full.logging = true
    }
}

headless.mode = false

webdriver {
  driver = chrome
  capabilities {
    browserName = "chrome"
    acceptInsecureCerts = true
    "goog:chromeOptions" {
      args = ["remote-allow-origins=*", "no-sandbox", "ignore-certificate-errors",
              "--window-size=1000,800", "incognito", "disable-infobars", "disable-gpu",
              "disable-dev-shm-usage", "disable-extensions"]
    }
  }
}

environments {
    default {
        webdriver.base.url       = "https://www.service.nsw.gov.au/transaction/check-motor-vehicle-stamp-duty"
        restapi.baseurl          = "https://openlibrary.org"
        restapi.spacex.baseurl   = "https://api.spacexdata.com/v4/launches/latest"
        restapi.emp.baseurl      = "https://dummy.restapiexample.com/api/v1"
    }
}
```

| Config Key              | Base URL                                         | Used By         |
|-------------------------|--------------------------------------------------|-----------------|
| `webdriver.base.url`    | `https://www.service.nsw.gov.au/...`             | UI tests        |
| `restapi.baseurl`       | `https://openlibrary.org`                        | AuthorApi       |
| `restapi.spacex.baseurl`| `https://api.spacexdata.com/v4/launches/latest`  | SpacexApi       |
| `restapi.emp.baseurl`   | `https://dummy.restapiexample.com/api/v1`        | EmployeeApi     |

---

## APIs Under Test

### 1. Open Library API — GET

| Property     | Value                                    |
|--------------|------------------------------------------|
| Base URL     | `https://openlibrary.org`                |
| Endpoint     | `/authors/{authorId}.json`               |
| HTTP Method  | `GET`                                    |
| Config Key   | `restapi.baseurl`                        |
| Cucumber Tag | `@TC_03`                                 |
| Action Class | `api.AuthorApi`                          |

**What it does:**
- Reads the `authorId` from `testdata/authorData.json` via `JsonReader`
- Sends a GET request: `GET https://openlibrary.org/authors/OL1A.json`
- Deserializes the response into the `Author` POJO using Jackson
- Validates `personal_name` matches the expected value from test data
- Validates `alternate_names` list contains the expected alternate name

**RestAssured call in `AuthorApi.java`:**
```java
response = SerenityRest
        .given()
        .baseUri(baseUrl)
        .when()
        .get("/authors/" + authorId + ".json");
```

**POJO classes:**

| Class              | Package      | Fields mapped                              |
|--------------------|--------------|--------------------------------------------|
| `Author.java`      | `api.pojo`   | `name`, `personal_name`, `birth_date`, `type` |
| `Type.java`        | `api.pojo`   | `key`                                      |

**Test data:** `src/test/resources/testdata/authorData.json`
```json
{
  "author": {
    "authorId": "OL1A",
    "personalName": "Sachi Routray",
    "alternateName": "Yugashrashta Sachi Routray"
  }
}
```

---

### 2. SpaceX Latest Launch API — GET

| Property     | Value                                                   |
|--------------|---------------------------------------------------------|
| Base URL     | `https://api.spacexdata.com/v4/launches/latest`         |
| Endpoint     | *(base URL is the full endpoint)*                       |
| HTTP Method  | `GET`                                                   |
| Config Key   | `restapi.spacex.baseurl`                                |
| Cucumber Tag | `@TC_04`                                                |
| Action Class | `api.SpacexApi`                                         |

**What it does:**
- Sends a GET request to the full SpaceX launches/latest endpoint
- Deserializes the response into the `Spacex` POJO
- Validates the `cores` list is non-empty
- Validates each core entry has a non-null `core` ID and `flight` number

**RestAssured call in `SpacexApi.java`:**
```java
response = SerenityRest
        .given()
        .baseUri(baseUrl)
        .when()
        .get();
```

**POJO classes:**

| Class         | Package               | Fields mapped                               |
|---------------|-----------------------|---------------------------------------------|
| `Spacex.java` | `api.pojo.spacexpojo` | `links`, `rocket`, `success`, `crew`, `cores` |
| `Core.java`   | `api.pojo.spacexpojo` | `core`, `flight`                            |
| `Links.java`  | `api.pojo.spacexpojo` | launch link fields                          |

---

### 3. Dummy REST API — Employee POST

| Property     | Value                                           |
|--------------|-------------------------------------------------|
| Base URL     | `https://dummy.restapiexample.com/api/v1`       |
| Endpoint     | `/create`                                       |
| HTTP Method  | `POST`                                          |
| Config Key   | `restapi.emp.baseurl`                           |
| Cucumber Tag | `@TC_05`                                        |
| Action Class | `api.EmployeeApi`                               |

**What it does:**
- Reads the JSON payload from `payload/createEmployee.json`
- Sends a POST request with `Content-Type: application/json`
- Validates the response status code is `200`
- Validates `status` field equals `"success"`
- Validates `data.name`, `data.salary`, and `data.age` match the submitted payload values

**RestAssured call in `EmployeeApi.java`:**
```java
File payload = new File("src/test/resources/payload/createEmployee.json");

response = SerenityRest
        .given()
        .baseUri(baseUrl)
        .contentType("application/json")
        .body(payload)
        .when()
        .post("/create");
```

**POST payload:** `src/test/resources/payload/createEmployee.json`
```json
{
  "name": "Ron",
  "salary": "123",
  "age": "23"
}
```

**Assertions in `EmployeeApi.java`:**
```java
assertThat(response.getStatusCode(), equalTo(200));
assertThat(jsonPath.getString("status"), equalTo("success"));
assertThat(jsonPath.getString("data.name"), equalTo(name));
assertThat(jsonPath.getString("data.salary"), equalTo(salary));
assertThat(jsonPath.getString("data.age"), equalTo(age));
```

---

## BDD Feature File

**File:** `src/test/resources/features/nsw/api.author.feature`

```gherkin
Feature: Open Library and validate details of Author - API Validation

  @API @Task3 @TC_03
  Scenario: Validate specific author details from Open Library using JSON test data
    When user sets the API base URL
    When I request details for author from json
    Then validate personal name from json
    And validate alternate name from json

  @API @Task3 @TC_04
  Scenario: Validate specific spacex
    When user sets the API base URL and verifies info

  @API @Task3 @TC_05
  Scenario: Post Employee api
    When user sets the API base URL and post emp info
```

---

## Step Definitions

**File:** `src/test/java/api/steps/AuthorSteps.java`

This single class handles all API step bindings. It references three API action classes — `AuthorApi`, `SpacexApi`, and `EmployeeApi` — which are managed by Serenity's dependency injection.

| Gherkin Step                                          | Method Called                              | API              |
|-------------------------------------------------------|--------------------------------------------|------------------|
| `user sets the API base URL`                          | `AuthorApi.setBaseUrl()`                   | Open Library     |
| `I request details for author from json`              | `AuthorApi.getAuthorFromJson()`            | Open Library GET |
| `validate personal name from json`                    | `AuthorApi.validatePersonalNameFromPojo()` | Open Library GET |
| `validate alternate name from json`                   | `AuthorApi.validateAlternateName()`        | Open Library GET |
| `user sets the API base URL and verifies info`        | `SpacexApi.setBaseUrl()` + `getSpacexResponse()` + `validateCoreDataFromPojo()` | SpaceX GET |
| `user sets the API base URL and post emp info`        | `EmployeeApi.setBaseUrl()` + `postNewEmployee()` + `validatePostNewEmployeeResponse()` | Employee POST |

---

## Package Structure Detail

```
src/test/java/
│
├── api/                        # REST API action layer
│   ├── AuthorApi.java          # Open Library GET logic + assertions
│   ├── SpacexApi.java          # SpaceX GET logic + POJO assertions
│   ├── EmployeeApi.java        # Employee POST logic + JSON path assertions
│   │
│   ├── steps/
│   │   └── AuthorSteps.java    # All @When/@Then step bindings for API scenarios
│   │
│   ├── runner/
│   │   └── ApiCucumberTestSuite.java   # Runs API scenarios by tag
│   │
│   └── pojo/
│       ├── Author.java         # Maps Open Library author response fields
│       ├── Type.java           # Maps author type sub-object
│       └── spacexpojo/
│           ├── Spacex.java     # Maps SpaceX launch response
│           ├── Core.java       # Maps SpaceX core data within launch
│           └── Links.java      # Maps SpaceX launch links
│
├── ui/                         # Selenium UI automation layer
│   ├── pages/
│   │   ├── StampDutyLandingPage.java
│   │   ├── MotorVehicleRegistrationPage.java
│   │   └── CalculatorPopupPage.java
│   ├── steps/
│   │   └── StepDefinitions.java
│   └── runner/
│       └── CucumberTestSuite.java
│
└── utils/
    ├── JsonReader.java         # Jackson-based reader for authorData.json and createEmployee.json
    └── HelperMethods.java      # Shared WebDriver utilities (waits, scroll, page load)
```

---

## Test Runners

### API Test Runner

**File:** `src/test/java/api/runner/ApiCucumberTestSuite.java`

```java
@Suite
@IncludeEngines("cucumber")
@IncludeTags("TC_05")                        // Change to TC_03, TC_04, or TC_05 as needed
@SelectPackages("features.nsw")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME,
    value = "net.serenitybdd.cucumber.core.plugin.SerenityReporterParallel,pretty,timeline:build/test-results/timeline")
public class ApiCucumberTestSuite {}
```

> Update `@IncludeTags` to run the desired scenario. Use `@API` to run all API scenarios.

### UI Test Runner

**File:** `src/test/java/ui/runner/CucumberTestSuite.java`

```java
@Suite
@IncludeEngines("cucumber")
@IncludeTags("TC_01")
@SelectClasspathResource("/features/nsw")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME,
    value = "net.serenitybdd.cucumber.core.plugin.SerenityReporterParallel,pretty,timeline:build/test-results/timeline")
public class CucumberTestSuite {}
```

---

## Test Data

| File                                          | Used By          | Purpose                                  |
|-----------------------------------------------|------------------|------------------------------------------|
| `src/test/resources/testdata/authorData.json` | `AuthorApi`      | Author ID, expected personal name, alternate name for GET assertions |
| `src/test/resources/payload/createEmployee.json` | `EmployeeApi` | Request body for POST `/create` and expected response values |
| `src/test/resources/testdata/spacex.json`     | (reference)      | SpaceX reference data                    |

Test data is loaded by `utils.JsonReader` using Jackson's `ObjectMapper`:

```java
// Reading author test data
String authorId = JsonReader.getValue("authorId");

// Reading employee payload for assertion
JsonNode emp = JsonReader.empData();
String name   = emp.get("name").asText();
String salary = emp.get("salary").asText();
String age    = emp.get("age").asText();
```

---

## Cucumber Tags Reference

| Tag      | Scenario                                      | Type |
|----------|-----------------------------------------------|------|
| `TC_01`  | NSW Stamp Duty UI calculator test             | UI   |
| `TC_03`  | Open Library — GET author details validation  | API  |
| `TC_04`  | SpaceX — GET latest launch validation         | API  |
| `TC_05`  | Employee — POST create new employee           | API  |
| `@API`   | All API scenarios                             | API  |
| `@Task3` | Group tag for all API task scenarios          | API  |

---

## Executing Tests

### Prerequisites

- Java 17+
- Maven 3.6+
- Google Chrome (latest stable)

Verify:
```bash
java -version
mvn -version
```

### Clone and install

```bash
git clone <repository-url>
cd CucumberBDDSerenityJavaSeleniumUIAndApi-main
mvn clean install -DskipTests
```

### Run all tests

```bash
mvn clean verify
```

### Run by tag

```bash
# Open Library GET test
mvn clean verify -Dtags="TC_03"

# SpaceX GET test
mvn clean verify -Dtags="TC_04"

# Employee POST test
mvn clean verify -Dtags="TC_05"

# All API tests
mvn clean verify -Dtags="@API"

# UI Stamp Duty test
mvn clean verify -Dtags="TC_01"
```

### Switching environments

```bash
mvn clean verify -Denvironment=staging
```

---

## Reports

After `mvn clean verify`, Serenity generates a rich HTML report:

```
target/site/serenity/index.html
```

To generate the report separately:

```bash
mvn serenity:aggregate
```

**Report includes:**
- Test outcome summary per feature and scenario
- Step-level breakdown with request/response detail for API calls
- Screenshots captured on test failure
- Living documentation view of Gherkin scenarios

---

## Best Practices

- **Use `SerenityRest` for all API calls** — not raw RestAssured — so every request and response is captured automatically in the Serenity report.
- **Keep base URLs in `serenity.conf`** — never hard-code URLs in action classes or step definitions.
- **Use POJOs for response assertions** — deserializing responses with Jackson POJOs produces clearer assertion messages and is more maintainable than raw JSON path strings.
- **Keep step definitions thin** — step methods should call a single action class method. All request logic and assertions belong in the API action class.
- **Drive test data from JSON files** — input values and expected results come from `testdata/` and `payload/` JSON files, not from the feature file or step code.
- **Tag every scenario** — use tags like `@TC_03`, `@API`, `@smoke` to enable selective execution from the runner or Maven command line.
- **Always run with `mvn clean`** — prevents stale Serenity output from previous runs polluting the aggregate report.
