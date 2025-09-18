package ui;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pojos.Employee;
import ui.pages.BenefitsPage;
import ui.pages.LogInPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;


import java.util.*;


@Tag("ui")
public class UITest {


    WebDriver webDriver;

    public UITest() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);

        WebDriver driver = new ChromeDriver(options);

        ((JavascriptExecutor) driver).executeScript(
                "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})"
        );
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        webDriver = new ChromeDriver(options);
    }

    public String user = "TestUser802";
    public String password = "cf@ATyv+XI*{";


    @Test
    public void missingCredentialsTest() {
        LogInPage page = new LogInPage(webDriver);
        List<String> errors = page.loginInvalidUser("", "").getErrorMessages();
        assertThat(errors, hasItem("There were one or more problems that prevented you from logging in:"));
        assertThat(errors, hasItem("The Username field is required."));
        assertThat(errors, hasItem("The Password field is required."));

    }

    @Test
    public void clickDashboardWithoutLogInTest() {

        LogInPage page = new LogInPage(webDriver);
        String url = webDriver.getCurrentUrl();
        page.clickDashboard();
        String dashboardUrl = webDriver.getCurrentUrl();
        assertThat("should be login page", dashboardUrl, is(url));

    }

    @Order(1)

    @ParameterizedTest
    @CsvFileSource(resources = "/data/employee.csv", numLinesToSkip = 1)
    public void addNewEmployeeAndVerifyTableTest(String fistName, String lastName, int dependents) {
        Employee e = new Employee();
        e.setFirstName(fistName);
        e.setLastName(lastName);
        e.setDependants(dependents);
        BenefitsPage bp = new LogInPage(webDriver).loginValidUser(user, password).addNewEmployee(e);
        int currentSize = bp.tableSize;
        Employee row = bp.findEmployeeByName(e.getFirstName(), e.getLastName());
        int updatedtableSize = bp.tableSize;

        assertThat("First Name equals", row.getFirstName(), is(e.getFirstName()));
        assertThat("Last Name equals", row.getLastName(), is(e.getLastName()));
        assertThat("dependets equals", row.getDependants(), is(e.getDependants()));
        assertThat("Table is bigger", updatedtableSize, is(currentSize + 1));


    }

    @Order(2)
    @ParameterizedTest
    @CsvFileSource(resources = "/data/employee.csv", numLinesToSkip = 1)
    public void updateEmployee(String fistName, String lastName) {
        Employee e = new Employee();
        e.setFirstName(fistName);
        e.setLastName(lastName);

        BenefitsPage bp = new LogInPage(webDriver).loginValidUser(user, password);
        Employee row = bp.findEmployeeByName(e.getFirstName(), e.getLastName());
        e.setLastName("updated");

        bp.updateEmployee(row, e);
        assertThat("First Name equals", row.getFirstName(), is(e.getFirstName()));
        assertThat("Last Name equals", row.getLastName(), is(e.getLastName()));
        assertThat("dependets equals", row.getDependants(), is(e.getDependants()));

    }

    @Order(3)
    @ParameterizedTest
    @CsvFileSource(resources = "/data/employee.csv", numLinesToSkip = 1)
    public void deleteEmployee(String fistName, String lastName) {
        Employee e = new Employee();
        e.setFirstName(fistName);
        e.setLastName("updated");

        BenefitsPage bp = new LogInPage(webDriver).loginValidUser(user, password);
        int currentSize = bp.tableSize;
        bp.deleteEmployee(e);
        int updatedTableSize = bp.tableSize;


        assertThat("Table is smaller", updatedTableSize, is(currentSize - 1));


    }

    @AfterEach
    public void closeDriver() {
        webDriver.close();
    }


}