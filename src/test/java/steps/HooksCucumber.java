package steps;

import io.cucumber.java.*;
import loggerUtility.LoggerUtility;
import reportUtility.ReportUtility;
import sharedData.SharedData;

public class HooksCucumber extends BaseSteps {

    public HooksCucumber(SharedData sharedData) {
        super(sharedData);
    }

    @BeforeAll
    public static void before_all() {
        ReportUtility.initiateReport();
    }

    @Before(order = 1)
    public void beforeHooks(Scenario scenario) {
        LoggerUtility.startTest(scenario.getName(), scenario);
        ReportUtility.startTest(scenario.getName());
    }

    @Before("@UI")
    public void beforeUI(Scenario scenario) {
        sharedData.initializeDriver();
    }

    @After("@UI")
    public void afterHooks(Scenario scenario) {
        sharedData.quitDriver();
    }

    @After
    public void afterScenario(Scenario scenario) {
        LoggerUtility.endTest(scenario.getName());
        ReportUtility.endTest(scenario.getName());
    }

    @AfterAll
    public static void after_all() {
        ReportUtility.generateReport();
    }
}
