package sharedData.service.local;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import sharedData.BrowserService;
import xmlFiles.xmlNode.FrontEndConfig;

import java.time.Duration;
@Getter
public class ChromeBrowserService implements BrowserService {
    private WebDriver driver;

    @Override
    public void openBrowser(FrontEndConfig frontEndConfig) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = (ChromeOptions) getBrowserOptions(frontEndConfig);
        driver = new ChromeDriver(options);
        driver.get(frontEndConfig.url);
        driver.manage().window().maximize();
    }

    @Override
    public Object getBrowserOptions(FrontEndConfig frontEndConfig) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(frontEndConfig.gpu);
        //--headless=new
        if(frontEndConfig.headless){
            options.addArguments("--headless=new");
        }

        options.addArguments(frontEndConfig.infobars);
        options.addArguments(frontEndConfig.sandbox);
        options.setImplicitWaitTimeout(Duration.ofSeconds(frontEndConfig.implicitWait));
        options.setScriptTimeout(Duration.ofSeconds(frontEndConfig.scriptTimeout));
        options.setPageLoadTimeout(Duration.ofSeconds(frontEndConfig.pageLoadTimeout));
        return options;
    }
}
