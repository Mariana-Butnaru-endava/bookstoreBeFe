package sharedData.service.remote;

import lombok.Getter;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import sharedData.BrowserService;
import xmlFiles.xmlNode.FrontEndConfig;

import java.net.URL;
import java.time.Duration;
@Getter
public class ChromeBrowserRemoteService implements BrowserService {
    private WebDriver driver;

    @SneakyThrows
    @Override
    public void openBrowser(FrontEndConfig frontEndConfig) {
        ChromeOptions options = (ChromeOptions) getBrowserOptions(frontEndConfig);
        driver = new RemoteWebDriver(new URL(frontEndConfig.remoteUrl), options);
        driver.get(frontEndConfig.url);
        driver.manage().window().maximize();
    }

    @Override
    public Object getBrowserOptions(FrontEndConfig frontEndConfig) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(frontEndConfig.gpu);
        options.addArguments("--headless=new");
        options.addArguments(frontEndConfig.infobars);
        options.addArguments(frontEndConfig.sandbox);
        options.setImplicitWaitTimeout(Duration.ofSeconds(frontEndConfig.implicitWait));
        options.setScriptTimeout(Duration.ofSeconds(frontEndConfig.scriptTimeout));
        options.setPageLoadTimeout(Duration.ofSeconds(frontEndConfig.pageLoadTimeout));
        return options;
    }
}