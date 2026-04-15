package steps.frontend;

import actions.frontend.LoginUiActions;
import io.cucumber.java.en.When;
import sharedData.SharedData;
import steps.BaseSteps;

public class LoginSteps extends BaseSteps {

    private LoginUiActions loginUiActions;

    public LoginSteps(SharedData sharedData) {
        super(sharedData);
    }

    @When("I login into application")
    public void loginInApp() {
        loginUiActions = new LoginUiActions(sharedData.getDriver());
        loginUiActions.logIntoApp();
    }
}
