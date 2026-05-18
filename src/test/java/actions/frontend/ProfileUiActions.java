package actions.frontend;

import com.aventstack.extentreports.Status;
import context.DataContext;
import context.keys.RequestKeys;
import objectData.requestObject.Account;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.LoginPage;
import pages.ProfilePage;
import reportUtility.ReportUtility;

public class ProfileUiActions extends BaseUIActions {

    private ProfilePage profilePage;

    public ProfileUiActions(WebDriver driver) {
        super(driver);
    }

    public void logoutFromApp() {
        profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.logoutFromApp().isLoginButtonDisplayed(),"Login button not displayed");

        ReportUtility.attachReportLog(Status.PASS, "User successfully logged out");
    }
}
