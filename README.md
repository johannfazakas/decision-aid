# Decision Maker App

    Need help in choosing between products based on own set criterias and weights? This app does that.
    It has a React web app and an API based on microservices using Spring with Kotlin and Java.

## Requirements

    java 11
    postgresql 13.2
    node 10.23.0

    Other versions could work, these are mine.

## Components

### Decision Service

    Decisions domain API.
    technologies: Spring, Kotlin, Gradle.
    default port: 7032.

#### Run

```bash
cd dm-decision-service/
./gradlew bootRun
```

### Gateway Service
    Api Gateway for backend microservices platform.
    technologies: Spring Cloud Gateway, Java, Gradle
    default port: 7049

#### Run
```bash
cd dm-gateway-service
./gradlew bootRun
```

### Decision Maker UI

    Single page web app for Decision Maker application.
    technology: React, Node, Bootstrap.
    default host: 7085.

#### Run

```bash
cd dm-ui/
npm run start
```

### Decision Maker Test

    Project dedicated for API tests.
    technologies: Java, Cucumber, Gherkin, JUnit, Guice, Gradle.

#### Run

```bash
cd dm-test/
./gradlew cucumber
```
