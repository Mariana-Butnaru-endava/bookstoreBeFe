package steps.frontend;

import actions.frontend.LoginUiActions;
import actions.frontend.ProfileUiActions;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sharedData.SharedData;
import steps.BaseSteps;

public class ProfileSteps extends BaseSteps {

    private ProfileUiActions profileUiActions;

    public ProfileSteps(SharedData sharedData) {
        super(sharedData);
    }

    @Then("I logout from application")
    public void logoutFromApp() {
        profileUiActions = new ProfileUiActions(sharedData.getDriver());
        profileUiActions.logoutFromApp();
    }
}
