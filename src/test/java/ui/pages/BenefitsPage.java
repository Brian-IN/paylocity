package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pojos.Employee;

import java.util.List;


public class BenefitsPage extends BasePage {
    public BenefitsPage(WebDriver webDriver) {
        super(webDriver);
    }

    By LOG_OUT = By.xpath("//*/a[contains(text(), 'Log Out')]/@href");


    By ADD_EMPLOYEE_BUTTON = By.xpath("//*[@id=\"add\"]");
    By ADD_EMPLOYEE_MODAL = By.xpath("//*[@id=\"employeeModal\"]/div/div");

    By FIRST_NAME = By.xpath("//*[@id=\"firstName\"]");
    By LAST_NAME = By.xpath("//*[@id=\"lastName\"]/div/div");
    By DEPENDENTS = By.xpath("//*[@id=\"dependants\"]/div/div");

    By ADD_BUTTON = By.xpath("//*[@id=\"addEmployee\"]");
    By UPDATE_BUTTON = By.xpath("//*[@id=\"updateEmployee\"]");
    By CANCEL_BUTTON = By.xpath("//*[@id=\"employeeModal\"]/div/div/div[3]/button[contains(text(), 'Cancel')]");

    By EMPLOYEES_TABLE = By.xpath("//*[@id=\"employeesTable\"]");

    By EDIT = By.xpath("//*/tr/td/i[1]");
    By DELETE = By.xpath("//*/tr/td/i[2]");

    By EMPLOYEE_ID = By.xpath("//*/tr/td[1]");
    By EMPLOYEE_FIRST_NAME = By.xpath("//*/tr/td[2]");
    By EMPLOYEE_LAST_NAME = By.xpath("//*/tr/td[3]");



    public BenefitsPage addNewEmployee(Employee e) {
        fillForm(e);
        webDriver.findElement(ADD_BUTTON).click();
        return this;
    }

    public BenefitsPage updateEmployee(Employee e) {
        fillForm(e);
        webDriver.findElement(UPDATE_BUTTON).click();
        return this;
    }

    public BenefitsPage cancelAdd(Employee e) {
        fillForm(e);
        webDriver.findElement(CANCEL_BUTTON).click();
        return this;
    }

    public void fillForm(Employee e) {
        webDriver.findElement(FIRST_NAME).sendKeys(e.getFistName());
        webDriver.findElement(LAST_NAME).sendKeys(e.getLastName());
        webDriver.findElement(DEPENDENTS).sendKeys(e.getDependents());
    }

    public WebElement findEmployeeById(String id) {
        WebElement baseTable = webDriver.findElement(EMPLOYEES_TABLE);
        List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
        for (WebElement e : tableRows) {
            if (e.getText().equals(id))
                return e;
        }
        return null;
    }

    public WebElement findEmployeeByName(String firstName, String lastName) {
        WebElement baseTable = webDriver.findElement(EMPLOYEES_TABLE);
        List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
        for (WebElement e : tableRows) {
            if (e.findElement(EMPLOYEE_FIRST_NAME).getText().equals(firstName) &&
                    e.findElement(EMPLOYEE_LAST_NAME).getText().equals(lastName))
                return e;
        }
        return null;
    }


}
