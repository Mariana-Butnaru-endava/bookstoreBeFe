package steps.backend;

import actions.backend.AccountActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountServiceSteps {
    private AccountActions accountActions;

    @When("^I create a new account from backend$")
    public void createNewAccount() {
        accountActions = new AccountActions();
        accountActions.createAccount();
    }

    @And("I generate token for new account")
    public void iGenerateTokenForNewAccount() {
        accountActions.generateAccountToken();
    }

    @When("I get account")
    public void iGetNewAccount() {
        accountActions.getAccount();
    }

    @Then("I delete account from backend")
    public void iDeleteAccountFromBackend() {
        accountActions.deleteAccount();
    }
}
