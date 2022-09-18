package seleniumpageobjects;


import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import seleniumhelpers.SeleniumBase;

import static org.junit.Assert.*;


@NoArgsConstructor
public class HomePage {

	private static WebDriver driver;
	private WebDriverWait wait;
	private Log log = LogFactory.getLog(HomePage.class);
	SeleniumBase seleniumBase;

	@FindBy(how = How.XPATH, using = "//a[text()='Register']")
	private WebElement registerLink;

	@FindBy(how = How.XPATH, using = "//a[text()='Bill Pay']")
	private WebElement billPayLink;

	@FindBy(how = How.XPATH, using = "//input[@value='Log In']")
	private WebElement loginInBtn;

	@FindBy(how = How.NAME, using = "username")
	private WebElement userName;

	@FindBy(how = How.NAME, using = "password")
	private WebElement passowrd;

	@FindBy(how = How.ID, using = "payeeName")
	private WebElement payeeName;

	@FindBy(how = How.ID, using = "fromAccountId")
	private WebElement senderAccount;

	@FindBy(how = How.ID, using = "amount")
	private WebElement amount;



	public HomePage(WebDriver webdriver) {
		driver = webdriver;
		seleniumBase = new SeleniumBase(webdriver);
		wait = new WebDriverWait(driver, 20);
		PageFactory.initElements(webdriver, this);
	}

	/**
	 * Navigate to a URL
	 *
	 */
	public void goTo(String urlPath) {
		SeleniumBase.sleep(1000);
		assertFalse("URL cannot be blank!", urlPath.equals(StringUtils.EMPTY));
		driver.switchTo().defaultContent();
		driver.get(urlPath);
		SeleniumBase.sleep(1000);
	}

	
	/***
	 * Click on the Register User link.
	 */
	public void goToRegistrationPage()
	{
		assertTrue("Registration link is not visible.",SeleniumBase.isElementVisible(registerLink));
		registerLink.click();
		SeleniumBase.sleep(2000);
	}

	/***
	 * Click on the Bill Payment link.
	 */
	public void goToBillPayPage()
	{
		assertTrue("Bill Pay link is not visible.",SeleniumBase.isElementVisible(billPayLink));
		billPayLink.click();
		SeleniumBase.sleep(2000);
	}


	/***
	 * Enter Login Credentials.
	 */
	public void enterLoginDetails(String user,String password)
	{
		SeleniumBase.sleep(1000);
		this.userName.sendKeys(user);
		this.passowrd.sendKeys(password);
	}

	/***
	 * Click on the Login button.
	 */
	public void clickLoginInButton()
	{
		assertTrue("Login In button is not visible.",SeleniumBase.isElementVisible(loginInBtn));
		loginInBtn.click();
		SeleniumBase.sleep(2000);
	}


	/**
	 * This method is used to verify the transaction details
	 */
	public void verifyTransactionDetails(String senderAccNo,String payeeFirstName,String payeeLastName,String transactionAmt) {
		SeleniumBase.sleep(2000);
		assertEquals("Payee Name is incorrect",payeeName.getText(),payeeFirstName+" "+payeeLastName);
		assertEquals("Sender Account details are incorrect",senderAccount.getText(),senderAccNo);
		assertEquals("Transaction Amount is incorrect",amount.getText(),transactionAmt);
		SeleniumBase.sleep(2000);
	}
}
