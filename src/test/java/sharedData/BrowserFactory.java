package sharedData;

import org.openqa.selenium.WebDriver;
import sharedData.service.local.ChromeBrowserService;
import sharedData.service.remote.ChromeBrowserRemoteService;
import xmlFiles.GeneralXML;
import xmlFiles.xmlNode.Configuration;

public class BrowserFactory {

    public WebDriver getPreparedDriver() {
        String ciCD = System.getProperty("cicd");
        String browser = System.getProperty("browser");

        Configuration cfg = GeneralXML.createConfig(Configuration.class);

        if (Boolean.parseBoolean(ciCD)) {
            //trebuie pus headless pentru remote
            cfg.frontEndConfig.headless = true;

            switch (browser.toLowerCase()) {
                case "chrome" -> {
                    ChromeBrowserRemoteService browserService = new ChromeBrowserRemoteService();
                    browserService.openBrowser(cfg.frontEndConfig);
                    return browserService.getDriver();
                }
                default -> throw new IllegalStateException("Unexpected value: " + browser);
            }
        } else {
            browser = cfg.frontEndConfig.localBrowser;

            switch (browser.toLowerCase()) {
                case "chrome" -> {

                    ChromeBrowserService browserService = new ChromeBrowserService();
                    browserService.openBrowser(cfg.frontEndConfig);
                    return browserService.getDriver();
                }
                default -> throw new IllegalStateException("Unexpected value: " + browser);
            }
        }
    }
}
