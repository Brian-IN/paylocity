package ui;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.pages.BasePage;
import ui.pages.LogInPage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import static org.hamcrest.Matchers.hasProperty;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Tag("ui")
public class LoginTest {

    WebDriver webDriver  = new ChromeDriver();


    @Test
    public void missingCredentialsTest() {
        LogInPage page = new LogInPage(webDriver);
        List<String> errors = page.loginInvalidUser("", "").getErrorMessages();
        assertThat(errors,hasItem("There were one or more problems that prevented you from logging in:"));
        assertThat(errors,hasItem("The Username field is required."));
        assertThat(errors,hasItem("The Password field is required."));

    }

    @Test
    public void clickDashboardWithoutLogInTest() {
        LogInPage page = new LogInPage(webDriver);
        String url = webDriver.getCurrentUrl();
        page.clickDashboard();
        String dashboardUrl = webDriver.getCurrentUrl();
        assertThat("should be login page", dashboardUrl, is(url));

    }

    /* @Test
     public void test3() {
         webDriver.findElement(USER).sendKeys(invalidEmail);
         webDriver.findElement(PASS).sendKeys(invalidPassword);
         webDriver.findElement(LOGIN).click();
        // assertEquals("You shall not pass! Arr!", webDriver.findElement(ERROR).getText());

     }

     @Test
     public void test4() {
         webDriver.findElement(USER).sendKeys(invalidEmailFormat);
         webDriver.findElement(PASS).sendKeys(invalidPassword);
         webDriver.findElement(LOGIN).click();
         //assertEquals("Enter a valid email", webDriver.findElement(EMAIL_WRONG_FORMAT).getText());

     }


     @Test
     public void test5() {

         webDriver.findElement(LOGIN).click();
         //List<WebElement> errors = webDriver.findElements(REQUIRED_FIELDS);

         //assertEquals("Email is required",errors.get(0).getText());
         //assertEquals("Password is required",errors.get(1).getText());


     }

     @Test
     public void test6() {
         new Actions(webDriver).sendKeys(Keys.TAB).sendKeys(validEmail).sendKeys(Keys.TAB)
                 .sendKeys(validPassword).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).perform();
        // assertEquals("Welcome to Codility", webDriver.findElement(WELCOME).getText());

     }*/
    @AfterEach
    public void closeDriver() {
        webDriver.close();
    }


}