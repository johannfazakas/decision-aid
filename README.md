# Decision Aid App

    Need help in choosing between products based on own set criterias and weights? This app does that.
    It has a React web app and an API based on microservices using Spring with Kotlin and Java.

## Requirements

    java 11
    postgresql 13.2
    node 10.23.0

    Other versions could work, these are mine.

## Run with docker compose

```bash
export APP_ENV=dev
export DB_DATA=path/to/postgres/db/data
docker-compose build
docker-compose up -d
```

## Components

### Decision Service

    Decisions domain API.
    technologies: Spring, Kotlin, Gradle, Postgresql
    default port: 7032.

#### Run

```bash
cd da-decision-service/
./gradlew bootRun
```

### User Service

    User domain API.
    technologies: Spring, Java, Gradle, Postgresql
    default port: 7087.

#### Run

```bash
cd da-decision-service/
./gradlew bootRun
```

### Gateway Service

    Api Gateway for backend microservices platform.
    technologies: Spring Cloud Gateway, Java, Gradle
    default port: 7049

#### Run

```bash
cd da-gateway-service
./gradlew bootRun
```

### Decision Aid UI

    Single page web app for Decision Aid application.
    technology: React, Node, Bootstrap.
    default host: 7084.

#### Run

```bash
cd da-ui/
npm install
npm start
```

### Decision Aid Test

    Project dedicated for API tests.
    technologies: Java, Cucumber, Gherkin, JUnit, Guice, Gradle.

#### Run

```bash
cd da-test/
./gradlew cucumber
```
