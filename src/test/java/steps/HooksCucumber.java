package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import loggerUtility.LoggerUtility;
import sharedData.SharedData;

public class HooksCucumber extends BaseSteps {

    public HooksCucumber(SharedData sharedData) {
        super(sharedData);
    }

    @Before(order = 1)
    public void beforeHooks(Scenario scenario) {

        LoggerUtility.startTest(scenario.getName(), scenario);
    }

    @Before("@UI")
    public void beforeUI(Scenario scenario) {

        sharedData.initializeDriver();
    }

    @After
    public void afterHooks(Scenario scenario) {
        sharedData.quitDriver();
        LoggerUtility.endTest(scenario.getName());
    }
}
