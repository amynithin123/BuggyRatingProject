package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(

		//features = ".//Features/Login.feature", glue = "stepDefinitions", dryRun =false, monochrome = true,// --> To run individual feature files, rename the feature file to run

		features = ".//Features/", glue = "stepDefinitions", dryRun = false, monochrome = true, // --> to run all feature files

		plugin = { "pretty", "json:target/MyReports/report.json", "junit:target/MyReports/report.xml",
				"html:target/MyReports/report.html" }, publish = true

)

public class Runner {

}
