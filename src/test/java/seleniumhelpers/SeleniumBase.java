package seleniumhelpers;


import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;


public class SeleniumBase {

	private static final int ELEMENT_WAIT_TIMEOUT_IN_SECONDS = 30;
	private static final int PAGE_WAIT_TIMEOUT_IN_SECONDS = 60;

	static WebDriver driver;

	public SeleniumBase(WebDriver webdriver) {
		driver = webdriver;
		PageFactory.initElements(driver, this);
	}

	// Is Element Present

	/**
	 * @param element
	 *            - {@link WebElement}.
	 * @return true if element present; else false.
	 */
	public static boolean isElementPresent(final WebElement element) {
		try {
			element.getTagName();
		} catch (final NoSuchElementException ignored) {
			return false;
		} catch (final StaleElementReferenceException ignored) {
			return false;
		}
		return true;
	}

	/**
	 * @param by
	 *            - {@link WebElement} as {@link By} object.
	 * @return true if element present; else false.
	 */
	public static boolean isElementPresent(final By by) {
		return driver.findElements(by).size() > 0;
	}

	// Is Element Visible

	/**
	 * @param element
	 *            - {@link WebElement}.
	 * @return true if element visible; else false.
	 */
	public static boolean isElementVisible(final WebElement element) {
		try {
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	/**
	 * @param by
	 *            - {@link WebElement} as {@link By} object.
	 * @return true if element visible; else false.
	 */
	public static boolean isElementVisible(final By by) {
		return driver.findElement(by).isDisplayed();
	}

	// Is Any Text Present

	/**
	 * @param element
	 *            - {@link WebElement}.
	 * @return true if any text present; else false.
	 */
	public static boolean isAnyTextPresent(final WebElement element) {
		final String text = element.getText();
		return StringUtils.isNotBlank(text);
	}

	/**
	 * @param by
	 *            - {@link WebElement} as {@link By} object.
	 * @return true if any text present; else false.
	 */
	public static boolean isAnyTextPresent(final By by) {
		return isAnyTextPresent(driver.findElement(by));
	}

	// Wait For Element

	/**
	 * Wait for {@link WebElement} is present. 5 seconds, tops.
	 * 
	 * @param element
	 *            - {@link WebElement}.
	 */
	public static void waitForElement(final WebElement element) {
		waitForElement(element, ELEMENT_WAIT_TIMEOUT_IN_SECONDS);
	}

	/**
	 * Wait for {@link WebElement} is present.
	 * 
	 * @param element
	 *            - {@link WebElement}.
	 * @param timeoutInSeconds
	 *            - How long the {@link WebDriver} wait for element.
	 */
	public static void waitForElement(final WebElement element, final int timeoutInSeconds) {
		final WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Wait for {@link WebElement} is present. 5 seconds, tops.
	 * 
	 * @param by
	 *            - {@link WebElement} as {@link By} object.
	 */
	public static void waitForElement(final By by) {
		waitForElement(by, ELEMENT_WAIT_TIMEOUT_IN_SECONDS);
	}

	/**
	 * Wait for {@link WebElement} is present.
	 * 
	 * @param by
	 *            - {@link WebElement} as By object.
	 * @param timeoutInSeconds
	 *            - How long the {@link WebDriver} wait for element.
	 */
	public static void waitForElement(final By by, final int timeoutInSeconds) {
		final WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	// Wait For Element Is Invisible

	/**
	 * Wait for {@link WebElement} is invisible. 5 seconds, tops.
	 * 
	 * @param by
	 *            - {@link WebElement} as {@link By} object.
	 */
	public static void waitForElementIsInvisible(final By by) {
		final WebDriverWait wait = new WebDriverWait(driver, ELEMENT_WAIT_TIMEOUT_IN_SECONDS);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	/**
	 * Wait for page load. 60 seconds, tops.
	 */
	public static void waitForPageLoad() {
		waitForPageLoad(PAGE_WAIT_TIMEOUT_IN_SECONDS);
	}

	/**
	 * Wait for page load.
	 * 
	 * @param timeoutInSeconds
	 *            - How long the {@link WebDriver} wait for page load.
	 */
	public static void waitForPageLoad(final int timeoutInSeconds) {
		waitForElement(By.tagName("html"), timeoutInSeconds);
	}


	/**
	 * java script click
	 * 
	 */
	public static void jsClick(final WebElement element) {
		final JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	// Zoom



	// Take A Screenshot

	/**
	 * Take a screenshot.
	 * 
	 * @param path
	 *            - Directory and Name of screenshot.
	 */
	public static void takeScreenshot(final String path) {
		final File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(path));
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void sleep(final int millis) {

		try {
			Thread.sleep(millis);
		} catch (final InterruptedException e) {
			// Nothing happens.
		}
	}
}

