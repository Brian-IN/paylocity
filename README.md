## How to run
```
./gradlew build 
```



### All Tests
```
./gradlew clean test --no-build-cache
```

### UI Tests
```
./gradlew clean uiTests --no-build-cache
```


### API Tests
```
./gradlew clean apiTests --no-build-cache
```



## Allure 
Generate Allure reports (needs additional instalation)
```java
allure generate allure-results --clean -o allure-report
Report successfully generated to allure-report
allure open .\allure-report\
```
