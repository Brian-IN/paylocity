## How to run
```
./gradlew build 
```



### All Tests
```
./gradlew clean test --no-build-cache
```

### ui Tests
```
./gradlew clean uiTests --no-build-cache
```


### api Tests
```
./gradlew clean apiTests --no-build-cache
```



## Allure 
Generate Allure reports (needs additional installation)
```
allure generate allure-results --clean -o allure-report
allure open .\allure-report\
```
