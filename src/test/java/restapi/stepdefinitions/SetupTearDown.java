package restapi.stepdefinitions;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import restapi.utilities.ConfigurationProperties;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class SetupTearDown {
	private static Log log = LogFactory.getLog(SetupTearDown.class);


	@BeforeStep
	public void beforeStep(Scenario scenario) throws Throwable {
		log.info("The execution scenario step before" +scenario.getLine());
	}


	@Before
	public static void setUp() {

			Map<String, Object> preferences = new Hashtable<String, Object>();
			preferences.put("profile.default_content_settings.popups", 0);
			// disable flash and the PDF viewer
			preferences.put("plugins.plugins_disabled", new String[]{
					"Adobe Flash Player", "Chrome PDF Viewer"});

			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", preferences);
			options.addArguments(ConfigurationProperties.getPropertyValueByKey("chromeOptionsUseIncognito"));
			options.addArguments(ConfigurationProperties.getPropertyValueByKey("chromeOptionsDisableInfoBar"));
			options.addArguments(ConfigurationProperties.getPropertyValueByKey("chromeOptionsDisableGaia"));
			options.addArguments(ConfigurationProperties.getPropertyValueByKey("chromeOptionsStartMaximized"));
			options.addArguments("auth-server-whitelist='_'");
			options.addArguments("--disable-gpu");
			options.addArguments("window-size=1200x600");
			System.setProperty(ConfigurationProperties.getPropertyValueByKey("chromeDriverSystemProperty"),
					ConfigurationProperties.getPropertyValueByKey("chromeDriver"));
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			if (null == CommonStepData.baseInstanceDriver) {
				CommonStepData.baseInstanceDriver = new ChromeDriver(capabilities);
			}
			if (CommonStepData.baseInstanceDriver != null) {
			CommonStepData.baseInstanceDriver.manage().timeouts().implicitlyWait(
					Integer.parseInt(ConfigurationProperties.getPropertyValueByKey("implicitWaitSeconds")),
					TimeUnit.SECONDS);
		}
	}


	@After
	public void tearDown(Scenario scenario) throws Throwable{
		try {
			if (scenario.isFailed() && ConfigurationProperties.getPropertyValueByKey("screenshotcapture").equals("true")) {
				final byte[] screenshot = ((TakesScreenshot) CommonStepData.baseInstanceDriver).getScreenshotAs(OutputType.BYTES);
			}
		} catch (WebDriverException somePlatformsDontSupportScreenshots) {
			log.error(somePlatformsDontSupportScreenshots.getMessage());echo "# ParaBankDemoApplication" >> README.md
		} finally {
			CommonStepData.baseInstanceDriver.manage().deleteAllCookies();
			CommonStepData.baseInstanceDriver.quit();
		}

		log.info("Successfully killed chromedriver instances.");
	}



}
