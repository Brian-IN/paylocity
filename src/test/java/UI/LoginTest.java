package UI;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import UI.pages.BasePage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Tag("UI")
public class LoginTest extends  BasePage {

    String validEmail= "login@codility.com";
    String invalidEmail= "unknown@codility.com";
    String invalidEmailFormat= "codility.com";

    String validPassword= "password";
    String invalidPassword= "password";

    By USER = By.xpath("//*[@id=\"Username\"]");
    By PASS = By.xpath("//*[@id=\"Password\"]");
    By LOGIN = By.xpath("//*/button[contains(text(), 'Log In')]");




    @Test
    public void loginPageElementsPresent() {
        boolean present;

        try {
            webDriver.findElement(USER);
            webDriver.findElement(PASS);
            webDriver.findElement(LOGIN);
            present = true;


        }catch (NoSuchElementException e) {
            present = false;
        }
        assertTrue(present);

    }
   /* @Test
    public void test2() {
        webDriver.findElement(USER).sendKeys(validEmail);
        webDriver.findElement(PASS).sendKeys(validPassword);
        webDriver.findElement(LOGIN).click();
        //assertEquals("Welcome to Codility", webDriver.findElement(WELCOME).getText());

    }

    @Test
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
    public void closeDriver(){
        webDriver.close();
    }



}