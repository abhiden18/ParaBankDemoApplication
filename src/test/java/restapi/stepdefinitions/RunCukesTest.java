
package restapi.stepdefinitions;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/features" },
        glue =  "restapi.stepdefinitions",
        tags = "@integrate",
        plugin = {"pretty", "json:target/cucumber.json" },
        monochrome = true)
public class RunCukesTest {
    //This class will remain blank
    @BeforeClass
    public static void setupProperties() {
        System.setProperty("classname", "RunCukesTest");
    }
}
