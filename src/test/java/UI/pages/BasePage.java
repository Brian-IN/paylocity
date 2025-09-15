package UI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Properties;


public class BasePage {
    public static Properties conf;


    By DASHBOARD = By.xpath("//*/a[contains(text(), 'Dashboard')]/@href");

    protected WebDriver webDriver;

    public BasePage() {

        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        webDriver.get("https://wmxrwq14uc.execute-api.us-east-1.amazonaws.com/Prod/Account/Login");

    }



}
