package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/*Runner class using TestNG for executing test.*/
@CucumberOptions(plugin = { "pretty","json:target/cucumber.json","junit:target/cucumber-reports/Cucumber.xml",
                            "html:target/cucumber-reports" }
                            , features = "src/test/java/features/"
                            ,glue={"stepDefinition"},tags = {"@SmokeTest"})

public class runCucumberTest extends AbstractTestNGCucumberTests {
}