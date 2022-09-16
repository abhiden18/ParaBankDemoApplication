package seleniumpageobjects;


import lombok.NoArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import restapi.entity.results;
import restapi.utilities.Operation;
import seleniumhelpers.SeleniumBase;

import static org.junit.Assert.assertTrue;


@NoArgsConstructor
public class UserRegistrationPage {

	private static WebDriver driver;
	private WebDriverWait wait;
	private Log log = LogFactory.getLog(UserRegistrationPage.class);
	SeleniumBase seleniumBase;

	@FindBy(how = How.ID, using = "customerForm")
	public WebElement registrationForm;

	@FindBy(how = How.ID, using = "customer.firstName")
	private WebElement firstName;

	@FindBy(how = How.ID, using = "customer.lastName")
	private WebElement lastName;

	@FindBy(how = How.ID, using = "customer.address.street")
	private WebElement address;

	@FindBy(how = How.ID, using = "customer.address.city")
	private WebElement city;

	@FindBy(how = How.ID, using = "customer.address.state")
	private WebElement state;

	@FindBy(how = How.ID, using = "customer.address.zipCode")
	private WebElement zipCode;

	@FindBy(how = How.ID, using = "customer.phoneNumber")
	private WebElement phone;

	@FindBy(how = How.ID, using = "customer.ssn")
	private WebElement ssn;

	@FindBy(how = How.ID, using = "customer.username")
	private WebElement userName;

	@FindBy(how = How.ID, using = "customer.password")
	private WebElement password;

	@FindBy(how = How.ID, using = "repeatedPassword")
	private WebElement repeatPassowrd;

	@FindBy(how = How.XPATH, using = "//div[@id='rightPanel']//input[contains(@class,'button')]")
	private WebElement registerBtn;

	@FindBy(how = How.XPATH, using = "//div[@id='rightPanel']//p")
	private WebElement registrationConfirmationMessage;

	@FindBy(how = How.ID, using = "customer.username.errors")
	private WebElement userNameError;


	public UserRegistrationPage(WebDriver webdriver) {
		driver = webdriver;
		seleniumBase = new SeleniumBase(webdriver);
		wait = new WebDriverWait(driver, 20);
		PageFactory.initElements(webdriver, this);
	}



	public void enterUserRegistrationDetails(results sender) {
		SeleniumBase.sleep(1000);
		assertTrue("First Name is not visible on Registration page.",SeleniumBase.isElementVisible(this.firstName));
		this.firstName.sendKeys(sender.getNameObj().getFirst());
		this.lastName.sendKeys(sender.getNameObj().getLast());
		this.address.sendKeys(sender.getLocationObj().getStreetObj().getNumber() + "," + sender.getLocationObj().getStreetObj().getName());
		this.city.sendKeys(sender.getLocationObj().getCity());
		this.state.sendKeys(sender.getLocationObj().getState());
		this.zipCode.sendKeys(sender.getLocationObj().getPostcode());
		this.phone.sendKeys(sender.getPhone());
		this.ssn.sendKeys(sender.getIdObj().getValue());
		this.userName.sendKeys("James Bond");
		this.password.sendKeys(sender.getLoginObj().getPassword());
		this.repeatPassowrd.sendKeys(sender.getLoginObj().getPassword());

		clickRegistrationButton();

		if(SeleniumBase.isElementVisible(userNameError)){
			this.userName.clear();
			this.userName.sendKeys(sender.getLoginObj().getUsername()+ Operation.getRandomNumber());
			this.password.sendKeys(sender.getLoginObj().getPassword());
			this.repeatPassowrd.sendKeys(sender.getLoginObj().getPassword());
			clickRegistrationButton();
		}


	}

	public void clickRegistrationButton() {
		assertTrue("Registration button not found!", SeleniumBase.isElementVisible(registerBtn));
		SeleniumBase.jsClick(registerBtn);

	}

	/**
	 * This method is used to validate different error messages present on Screen when any validation failed
	 */
	public String getMessageString() {
			return registrationConfirmationMessage.getText();
	}


}
