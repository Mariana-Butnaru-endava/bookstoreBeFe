package sharedData;

import loggerUtility.LoggerUtility;
import lombok.Getter;
import org.openqa.selenium.WebDriver;

@Getter
public class SharedData {
    private WebDriver driver;

    public void initializeDriver() {
        driver = new BrowserFactory().getPreparedDriver();
        LoggerUtility.logInfo("Initializing Driver");
    }

    public void quitDriver() {
        driver.close();
        driver.quit();
        LoggerUtility.logInfo("Driver closed with success");
    }
}
