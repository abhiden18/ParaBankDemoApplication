package seleniumpageobjects;


import lombok.NoArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import restapi.common.Constants;
import restapi.entity.Results;
import seleniumhelpers.SeleniumBase;

import static org.junit.Assert.assertTrue;


@NoArgsConstructor
public class BillPaymentPage {

	private static WebDriver driver;
	private WebDriverWait wait;
	private Log log = LogFactory.getLog(BillPaymentPage.class);
	SeleniumBase seleniumBase;

	@FindBy(how = How.NAME, using = "payee.name")
	private WebElement payeeName;

	@FindBy(how = How.NAME, using = "payee.address.street")
	private WebElement address;

	@FindBy(how = How.NAME, using = "payee.address.city")
	private WebElement city;

	@FindBy(how = How.NAME, using = "payee.address.state")
	private WebElement state;

	@FindBy(how = How.NAME, using = "payee.address.zipCode")
	private WebElement zipCode;

	@FindBy(how = How.NAME, using = "payee.phoneNumber")
	private WebElement phoneNumber;

	@FindBy(how = How.NAME, using = "payee.accountNumber")
	private WebElement accountNumber;

	@FindBy(how = How.NAME, using = "verifyAccount")
	private WebElement verifyAccount;

	@FindBy(how = How.NAME, using = "amount")
	private WebElement amount;

	@FindBy(how = How.NAME, using = "fromAccountId")
	private WebElement senderAccountNo;

	@FindBy(how = How.XPATH, using = "//input[@value='Send Payment']")
	private WebElement submitBtn;

	@FindBy(how = How.XPATH, using = "//div[@Id='rightPanel']//p")
	private WebElement billPayConfirmationMessage;


	public BillPaymentPage(WebDriver webdriver) {
		driver = webdriver;
		seleniumBase = new SeleniumBase(webdriver);
		wait = new WebDriverWait(driver, 20);
		PageFactory.initElements(webdriver, this);
	}

	public String getSenderAccountDetails() {
		assertTrue("Sender Account details is not visible:",SeleniumBase.isElementVisible(senderAccountNo));
		Select senderAccDtl = new Select(senderAccountNo);
		return senderAccDtl.getFirstSelectedOption().getText().trim();
	}


	public void enterBillPaymentDetails(Results payee, String amount) {
		SeleniumBase.sleep(1000);
		this.payeeName.sendKeys(payee.getNameObj().getFirst()+" "+payee.getNameObj().getLast());
		this.address.sendKeys(payee.getLocationObj().getStreetObj().getNumber() + "," + payee.getLocationObj().getStreetObj().getName());
		this.city.sendKeys(payee.getLocationObj().getCity());
		this.state.sendKeys(payee.getLocationObj().getState());
		this.zipCode.sendKeys(payee.getLocationObj().getPostcode());
		this.phoneNumber.sendKeys(payee.getPhone());
		// Account No String hardcoded as New Account open link was not working
		this.accountNumber.sendKeys(Constants.payeeAccountNo);
		this.verifyAccount.sendKeys(Constants.payeeAccountNo);
		this.amount.sendKeys(amount);
	}


	public void clickSubmitButton() {
		assertTrue("Submit button not found on Registration page!", SeleniumBase.isElementVisible(submitBtn));
		SeleniumBase.jsClick(submitBtn);
	}


}
