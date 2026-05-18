package actions.frontend;

import com.aventstack.extentreports.Status;
import context.DataContext;
import context.keys.RequestKeys;
import objectData.requestObject.Account;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.LoginPage;
import reportUtility.ReportUtility;

public class LoginUiActions extends BaseUIActions {

    private LoginPage loginPage;

    public LoginUiActions(WebDriver driver) {
        super(driver);
    }

    public LoginPage logIntoApp() {
        Account account = DataContext.getData(RequestKeys.REQUEST_OBJECT.getKey(), Account.class);
        loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.loginIntoApplication(account).isLogoutButtonDisplayed(),"Logout button not displayed");

        ReportUtility.attachReportLog(Status.PASS, "User successfully logged in from frontend");
        return loginPage;
    }
}
