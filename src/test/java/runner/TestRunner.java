package runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty",
                "html:target/reports/cucumber-html-report",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber.json"},
        features = "src/test/java/resources/features/smoke_api.feature",
        glue = "steps"
)
public class TestRunner {}