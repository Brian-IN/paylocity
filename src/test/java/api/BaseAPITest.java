package api;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import ui.pages.BasePage;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.delete;

public class BaseAPITest {

    public static final String EMPLOYEE_ENDPOINT = "/employees";

    @BeforeAll
    public static void setup() {
        Properties prop = new Properties();

        try (InputStream input = BasePage.class.getClassLoader().getResourceAsStream("config/config.properties")) {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        RestAssured.baseURI = prop.getProperty("url") + prop.getProperty("apiEndpoint");
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .addHeader("Authorization", "Basic VGVzdFVzZXI4MDI6Y2ZAQVR5ditYSSp7")
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();

        RestAssured.requestSpecification = requestSpec;
        cleanDB();
    }

    public static void cleanDB() {

        Response response = get(EMPLOYEE_ENDPOINT);

        if (response.statusCode() == 200) {
            List<Map<String, Object>> employees = response.jsonPath().getList("");

            for (Map<String, Object> e : employees) {
                String id = (String) e.get("id");

                if (id != null && !id.isBlank()) {
                    delete(EMPLOYEE_ENDPOINT + "/" + id);
                }
            }
        }
    }
}
