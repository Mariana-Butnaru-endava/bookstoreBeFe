package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage extends BasePage {
    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[text()='Logout']")
    private WebElement logoutButton;

    public boolean isLogoutButtonDisplayed() {
        return logoutButton.isDisplayed();
    }

    public LoginPage logoutFromApp() {
        logoutButton.click();
        return new LoginPage(driver);
    }
}
