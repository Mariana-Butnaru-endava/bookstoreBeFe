package pages;

import loggerUtility.LoggerUtility;
import objectData.requestObject.Account;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userName")
    private WebElement userName;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(id = "login")
    private WebElement loginButton;

    public ProfilePage loginIntoApplication(Account account) {
        //driver.findElement(By.id("userName")).sendKeys(account.getUserName());
        userName.sendKeys(account.getUserName());
        LoggerUtility.logInfo("Username entered: " + account.getUserName());
        //driver.findElement(By.id("password")).sendKeys(account.getPassword());
        password.sendKeys(account.getPassword());
        LoggerUtility.logInfo("Password entered: " + account.getPassword());
        //driver.findElement(By.id("login")).click();
        loginButton.click();
        LoggerUtility.logInfo("Login button clicked");
        return new ProfilePage(driver);
    }
}
