package actions.frontend;

import context.DataContext;
import context.keys.RequestKeys;
import objectData.requestObject.Account;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginUiActions extends BaseUIActions {

    private LoginPage loginPage;

    public LoginUiActions(WebDriver driver) {

        super(driver);
    }

    public void logIntoApp() {
        Account account = DataContext.getData(RequestKeys.REQUEST_OBJECT.getKey(), Account.class);
        loginPage = new LoginPage(driver);
        loginPage.loginIntoApplication(account);
    }
}
