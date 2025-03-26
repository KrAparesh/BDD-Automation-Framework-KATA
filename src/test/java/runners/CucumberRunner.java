package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        glue = {"stepDefinitions/apiDefinitions", "stepDefinitions/uiDefinitions"},
        plugin = {
                "pretty",
        },
        monochrome = true
)
public class CucumberRunner extends AbstractTestNGCucumberTests {

}