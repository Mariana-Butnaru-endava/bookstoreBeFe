package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/CreateAccount.feature",
        glue = {"steps"},
        plugin = {"pretty", "json:target/cucumber-html-reports/cucumber-reports.json"}
)
public class CreateAccountRunner extends AbstractTestNGCucumberTests {

}
