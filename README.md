# Decision Maker App
    Need help in choosing between products based on own set criterias and weights? This app does that.

    It has a React web app and an API based on some microservices using Spring with Kotlin.

## Requirements
    java 11
    postgresql 13.2
    node 10.23.0

    Other versions could work, these are mine.

## Run

### Decision Service
    
```bash
cd dm-decision-service
./gradlew bootRun
```
### Decision Maker UI
```bash
cd dm-ui
npm run start
```

## Test

### API Tests
```bash
cd dm-test
./gradlew cucumber
```
