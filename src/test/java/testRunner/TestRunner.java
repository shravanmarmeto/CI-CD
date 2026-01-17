package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/Cucumber", glue="stepdefinition", 
monochrome = true,tags="@Regression", plugin= {"html:target/cucumber.html"})
public class TestRunner extends AbstractTestNGCucumberTests{

}
