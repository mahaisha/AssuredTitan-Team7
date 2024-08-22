
package api.TestRunner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		plugin = {"pretty","html:target/cucumber.html",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
				},
		monochrome=false,  //console output color
		features = {
				"src/test/resources/feature/01UserLogin.feature",
				"src/test/resources/feature/02DieticianCreate.feature",
				"src/test/resources/feature/03DieticianGetAll.feature",
				"src/test/resources/feature/04DieticianPutById.feature",
				"src/test/resources/feature/05DieticianGetById.feature",
				"src/test/resources/feature/08DieticianDeleteById.feature"}, //location of feature files
		glue= {"api.StepDefinitions"}
		
		)

public class RunnerFile extends AbstractTestNGCucumberTests{
	@Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
				
		return super.scenarios();
    }

} 
