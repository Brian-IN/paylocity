package api;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pojos.Employee;

import static io.restassured.RestAssured.delete;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Tag("api")
public class ApiTest extends BaseAPITest {


    @Order(1)
    @CsvFileSource(resources = "/data/employeesAPI.csv", numLinesToSkip = 1)
    @ParameterizedTest
    public void verifyPostAPI(String username, String firstName, String lastName, int dependants) {
        Employee employee = new Employee();
        employee.setLastName(lastName);
        employee.setDependants(dependants);
        employee.setFirstName(firstName);
        employee.setUserName(username);

        float benefitsCost = (1000 + (dependants * 500)) / 26;
        float net = 52000 - benefitsCost;


        System.out.println(employee.toJson());
        String id = given()
                .body(employee.toJson())
                .when()
                .post(EMPLOYEE_ENDPOINT)
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("username", equalTo(employee.getUserName()))
                .body("firstName", equalTo(employee.getFirstName()))
                .body("lastName", equalTo(employee.getLastName()))
                .body("dependants", equalTo(employee.getDependants()))
                .body("salary", equalTo(52000))
                .extract()
                .path("id");

        given().get("/employees/" + id)
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("username", equalTo(employee.getUserName()));


    }

    @Order(2)
    @CsvFileSource(resources = "/data/employee.csv", numLinesToSkip = 1)
    @ParameterizedTest
    public void updateEmployee(String username, String firstName, String lastName) {
        Employee employee = new Employee();
        employee.setLastName(lastName);
        employee.setFirstName(firstName);
        employee.setUserName(username);
        String id = given()
                .body(employee.toJson())
                .post("/employees")
                .then()
                .extract()
                .path("id");

        employee.setDependants(10);
        employee.setId(id);
        String updatedBody = employee.toJson();

        given()
                .body(updatedBody)
                .when()
                .put(EMPLOYEE_ENDPOINT)
                .then()
                .statusCode(200)
                .body("lastName", equalTo(employee.getLastName()))
                .body("dependants", equalTo(employee.getDependants()));
    }

    @Order(2)
    @CsvFileSource(resources = "/data/employee.csv", numLinesToSkip = 1)
    @ParameterizedTest
    public void deleteEmployee(String username, String firstName, String lastName) {
        Employee employee = new Employee();
        employee.setLastName(lastName);
        employee.setFirstName(firstName);
        employee.setUserName(username);
        String id = given()
                .body(employee.toJson())
                .post("/employees")
                .then()
                .extract()
                .path("id");

        delete(EMPLOYEE_ENDPOINT + "/" + id)
                .then()
                .statusCode(200);

        get(EMPLOYEE_ENDPOINT + "/" + id)
                .then()
                .statusCode(404);
    }

    @Order(2)
    @CsvFileSource(resources = "/data/employee.csv", numLinesToSkip = 1)
    @ParameterizedTest
    public void createEmployeeWithoutUserName(String username, String firstName, String lastName) {
        Employee employee = new Employee();
        employee.setLastName(lastName);
        employee.setFirstName(firstName);
        employee.setUserName(username);
        given()
                .body(employee.toJson())
                .when()
                .post("/employees")
                .then()
                .statusCode(400);
    }


}