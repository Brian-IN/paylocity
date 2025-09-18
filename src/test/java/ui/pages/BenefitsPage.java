package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pojos.Employee;

import java.time.Duration;
import java.util.List;


public class BenefitsPage extends BasePage {
    Wait<WebDriver> wait;

    public BenefitsPage(WebDriver webDriver) {
        super(webDriver);
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        List<WebElement> tableRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(EMPLOYEES_TABLE));

        tableSize = tableRows.size();

    }

    By LOG_OUT = By.xpath("//*/a[contains(text(), 'Log Out')]/@href");


    By ADD_EMPLOYEE_BUTTON = By.xpath("//*[@id=\"add\"]");
    By ADD_EMPLOYEE_MODAL = By.xpath("//*[@id=\"employeeModal\"]/div/div");

    By FIRST_NAME = By.xpath("//*[@id=\"firstName\"]");
    By LAST_NAME = By.xpath("//*[@id=\"lastName\"]");
    By DEPENDENTS = By.xpath("//*[@id=\"dependants\"]");

    By ADD_BUTTON = By.xpath("//*[@id=\"addEmployee\"]");
    By UPDATE_BUTTON = By.xpath("//*[@id=\"updateEmployee\"]");
    By CANCEL_BUTTON = By.xpath("//*[@id=\"employeeModal\"]/div/div/div[3]/button[contains(text(), 'Cancel')]");
    By DELETE_BUTTON = By.xpath("//*[@id=\"deleteEmployee\"]");

    By EMPLOYEES_TABLE = By.xpath("//*[@id=\"employeesTable\"]/tbody/tr[*]");

    By EDIT = By.xpath("td/i[1]");
    By DELETE = By.xpath("td/i[2]");

    By EMPLOYEE_ID = By.xpath("td[1]");
    By EMPLOYEE_FIRST_NAME = By.xpath("td[2]");
    By EMPLOYEE_LAST_NAME = By.xpath("td[3]");


    public int tableSize;

    public BenefitsPage addNewEmployee(Employee e) {


        WebElement add = webDriver.findElement(ADD_EMPLOYEE_BUTTON);
        wait.until(d -> add.isDisplayed());

        webDriver.findElement(ADD_EMPLOYEE_BUTTON).click();
        fillForm(e);
        webDriver.findElement(ADD_BUTTON).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(EMPLOYEES_TABLE, tableSize + 1));
        tableSize++;
        return this;
    }

    public BenefitsPage updateEmployee(Employee actual, Employee updated) {
        WebElement table = webDriver.findElement(EMPLOYEES_TABLE);
        wait.until(d -> table.isDisplayed());
        wait.until(ExpectedConditions.numberOfElementsToBe(EMPLOYEES_TABLE, tableSize));


        WebElement row = findEmployeeRow(actual.getFirstName(), actual.getLastName());
        row.findElement(EDIT).click();
        fillForm(updated);
        webDriver.findElement(UPDATE_BUTTON).click();
        return this;
    }

    public BenefitsPage deleteEmployee(Employee e) {
        WebElement table = webDriver.findElement(EMPLOYEES_TABLE);
        wait.until(d -> table.isDisplayed());
        wait.until(ExpectedConditions.numberOfElementsToBe(EMPLOYEES_TABLE, tableSize));


        WebElement row = findEmployeeRow(e.getFirstName(), e.getLastName());
        row.findElement(DELETE).click();
        webDriver.findElement(DELETE_BUTTON).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(EMPLOYEES_TABLE, tableSize - 1));
        tableSize--;
        return this;
    }

    public BenefitsPage cancelAdd(Employee e) {
        fillForm(e);
        webDriver.findElement(CANCEL_BUTTON).click();
        return this;
    }

    public void fillForm(Employee e) {
        webDriver.findElement(FIRST_NAME).clear();
        webDriver.findElement(FIRST_NAME).sendKeys(e.getFirstName());
        webDriver.findElement(LAST_NAME).clear();
        webDriver.findElement(LAST_NAME).sendKeys(e.getLastName());
        webDriver.findElement(DEPENDENTS).clear();
        webDriver.findElement(DEPENDENTS).sendKeys(e.getDependants() + "");
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

    public Employee findEmployeeByName(String firstName, String lastName) {


        List<WebElement> tableRows = wait.until(ExpectedConditions.numberOfElementsToBe(EMPLOYEES_TABLE, tableSize));


        for (WebElement e : tableRows) {
            if (e.findElement(EMPLOYEE_FIRST_NAME).getText().equals(firstName) &&
                    e.findElement(EMPLOYEE_LAST_NAME).getText().equals(lastName)) {
                Employee employee = new Employee();
                employee.setId(e.findElement(By.cssSelector("tr>td:nth-child(1)")).getText());
                employee.setLastName(e.findElement(By.cssSelector("tr>td:nth-child(3)")).getText());
                employee.setFirstName(e.findElement(By.cssSelector("tr>td:nth-child(2)")).getText());
                employee.setDependants(Integer.parseInt(e.findElement(By.cssSelector("tr>td:nth-child(4)")).getText()));
                employee.setSalary(Double.parseDouble(e.findElement(By.cssSelector("tr>td:nth-child(5)")).getText()));
                employee.setGrossPay(Double.parseDouble(e.findElement(By.cssSelector("tr>td:nth-child(6)")).getText()));
                employee.setBenefitsCost(Double.parseDouble(e.findElement(By.cssSelector("tr>td:nth-child(7)")).getText()));
                employee.setNetPlay(Double.parseDouble(e.findElement(By.cssSelector("tr>td:nth-child(8)")).getText()));
                return employee;
            }
        }
        return null;
    }

    public WebElement findEmployeeRow(String firstName, String lastName) {

        wait.until(ExpectedConditions.numberOfElementsToBe(EMPLOYEES_TABLE, tableSize));
        List<WebElement> tableRows = webDriver.findElements(EMPLOYEES_TABLE);
        for (WebElement e : tableRows) {

            if (e.findElement(EMPLOYEE_FIRST_NAME).getText().equals(firstName) &&
                    e.findElement(EMPLOYEE_LAST_NAME).getText().equals(lastName)) {
                return e;
            }
        }
        return null;
    }


}
