package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

public class LogInPage extends BasePage {
    public LogInPage(WebDriver webDriver) {
        super(webDriver);
    }

    By USER = By.xpath("//*[@id=\"Username\"]");
    By PASS = By.xpath("//*[@id=\"Password\"]");
    By LOGIN = By.xpath("//*/button[contains(text(), 'Log In')]");
    By SUMMARY_ERRORS = By.xpath("//*/div[contains(@class, 'text-danger validation-summary-errors')]");


    public BenefitsPage loginValidUser(String user, String password) {
        webDriver.findElement(USER).sendKeys(user);
        webDriver.findElement(PASS).sendKeys(password);
        webDriver.findElement(LOGIN).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        return new BenefitsPage(webDriver);

    }

    public LogInPage loginInvalidUser(String user, String password) {
        webDriver.findElement(USER).sendKeys(user);
        webDriver.findElement(PASS).sendKeys(password);
        webDriver.findElement(LOGIN).click();
        return this;

    }

    public LogInPage clickDashboard() {
        webDriver.findElement(DASHBOARD).click();
        return this;
    }

    public List<String> getErrorMessages() {
        return Arrays.asList(webDriver.findElement(SUMMARY_ERRORS).getText().split("[\\r\\n]+"));
    }

}
