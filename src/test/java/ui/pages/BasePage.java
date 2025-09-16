package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;


public class BasePage {

    By DASHBOARD = By.xpath("//*/a[contains(text(), 'Dashboard')]");

    protected WebDriver webDriver;

    public BasePage(WebDriver webDriver) {

        this.webDriver = webDriver;
        Properties prop = new Properties();

        try (InputStream input = BasePage.class.getClassLoader().getResourceAsStream("config/config.properties")) {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        webDriver.get(prop.getProperty("url") + prop.getProperty("loginPage"));

    }

    public void closeDriver() {
        webDriver.close();
    }


}
