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
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import restapi.utilities.ConfigurationProperties;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class SetupTearDown {
	private static Log log = LogFactory.getLog(SetupTearDown.class);


	@BeforeStep
	public void beforeStep(Scenario scenario) throws Throwable {
		log.info("The execution scenario step before" +scenario.getLine());
	}



	public static void setUp() {
		String chromeDriver = ConfigurationProperties.getPropertyValueByKey("IsChromeBrowserActive");
		String ieDriver = ConfigurationProperties.getPropertyValueByKey("isIEDriverActive").toString();
		if (chromeDriver.equals("true")) {
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
		}else if (ieDriver.equals("true")) {
			System.setProperty("webdriver.ie32.driver", ConfigurationProperties.getPropertyValueByKey("ieDriver32Bit"));
			CommonStepData.baseInstanceDriver = new InternetExplorerDriver();
		} if (CommonStepData.baseInstanceDriver != null) {
			CommonStepData.baseInstanceDriver.manage().timeouts().implicitlyWait(
					Integer.parseInt(ConfigurationProperties.getPropertyValueByKey("implicitWaitSeconds")),
					TimeUnit.SECONDS);
		}
	}

	@Before
	public void before() throws MalformedURLException {
		setUp(); }


	//@AfterStep
	public void afterStep(Scenario scenario) throws Throwable {
		tearDownAfterStep(scenario);
	}


	public void tearDownAfterStep(Scenario scenario) throws IOException, InterruptedException
	{
		try {
			if (ConfigurationProperties.getPropertyValueByKey("screencapture").equals("true")) {
				final byte[] screenshot = ((TakesScreenshot) CommonStepData.baseInstanceDriver).getScreenshotAs(OutputType.BYTES);
				log.info("The execution scenario step after" +scenario.getLine());
				//scenario.embed(screenshot, "image/png"); // ... and embed it in
			}
		} catch (WebDriverException somePlatformsDontSupportScreenshots) {
			System.err.println(somePlatformsDontSupportScreenshots.getMessage());
		}
	}

	@After
	public void after(Scenario scenario) throws Throwable {
		tearDown(scenario);
	}

	public void tearDown(Scenario scenario) throws Throwable{
		try {
			if (scenario.isFailed() && ConfigurationProperties.getPropertyValueByKey("screencapture").equals("true")) {
				final byte[] screenshot = ((TakesScreenshot) CommonStepData.baseInstanceDriver).getScreenshotAs(OutputType.BYTES);
			}
		} catch (WebDriverException somePlatformsDontSupportScreenshots) {
			System.err.println(somePlatformsDontSupportScreenshots.getMessage());
		} finally {
			CommonStepData.baseInstanceDriver.manage().deleteAllCookies();

		}

		log.info("Successfully killed chromedriver instances.");
	}



}
