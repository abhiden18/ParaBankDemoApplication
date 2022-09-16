package restapi.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import restapi.entity.Result;
import restapi.entity.results;
import restapi.utilities.ConfigurationProperties;
import seleniumhelpers.SeleniumBase;
import seleniumpageobjects.BillPaymentPage;
import seleniumpageobjects.HomePage;
import seleniumpageobjects.UserRegistrationPage;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Cucumber.class)
public class BillPaymentStepDef {

    public results sender;
    public results payee;
    public Result result;
    public HomePage homePage;
    public UserRegistrationPage userRegistrationPage;
    public BillPaymentPage billPaymentPage;
    private Log log = LogFactory.getLog(BillPaymentStepDef.class);


    public BillPaymentStepDef() {

        homePage = new HomePage(CommonStepData.baseInstanceDriver);
        userRegistrationPage = new UserRegistrationPage(CommonStepData.baseInstanceDriver);
        billPaymentPage = new BillPaymentPage(CommonStepData.baseInstanceDriver);

    }

    /**
     * This method is used to Create request specification object for Random User generation API
     */
    @Given("I have the end point of random user generation API")
    public void i_have_the_end_point_of_random_user_generation_API() {
        RestAssured.baseURI = ConfigurationProperties.getPropertyValueByKey("randomUserAPI");
        log.info("Random API endpoint: "+RestAssured.baseURI);
    }


    /**
     * This method is used to call the random API to generate the Sender User
     */
    @When("I call the API to generate user as a Sender")
    public void i_call_the_API_to_generate_user_as_a_Sender() {
        log.info("Start call to Random API to create Sender User");

        RequestSpecification requestSpecification = given();
        Response response = requestSpecification.get();
        result = response.getBody().as(Result.class);
        assertTrue("Random API response is not null",
                result != null && result.getResultsObj().size() > 0);
        sender = result.getResultsObj().get(0);

        LoginUser.senderUserName = sender.getLoginObj().getUsername();
        LoginUser.senderPassword = sender.getLoginObj().getPassword();

        log.info("End call to Random API to create Sender User");
    }


    @Given("^I open the Para bank Application$")
    public void i_open_the_para_bank_application() {
        log.info("Start: open the Para bank Application");

        String appUrl = ConfigurationProperties.getPropertyValueByKey("guiURL");
        SeleniumBase.sleep(1000);
        homePage.goTo(appUrl);

        log.info("End: open the Para bank Application");
    }

    @When("^I click on user registration page$")
    public void i_click_on_user_registration_page() throws Throwable {
        homePage.goToRegistrationPage();
    }

    @When("^I enter the sender details and press save button$")
    public void i_enter_the_sender_details_and_press_save_button() throws Throwable {
        assertTrue("User Registration page is not open:",SeleniumBase.isElementVisible(userRegistrationPage.registrationForm));
        userRegistrationPage.enterUserRegistrationDetails(sender);
    }


    @Then("I see the user registration confirmation message prompt on screen as (.+)$")
    public void i_see_the_registration_confirmation_message_prompt_on_screen_as(String message) {
        assertEquals("Message is not found!", message,
                userRegistrationPage.getMessageString());
    }

    /**
     * This method is used to call the random API to generate the Payee User
     */
    @And("I call the API to generate user as a Payee")
    public void i_call_the_API_to_generate_user_as_a_Payee() {

        log.info("Start call to Random API to create Payee User");
        RequestSpecification requestSpecification = given();
        Response response = requestSpecification.get();
        result = response.getBody().as(Result.class);
        payee = result.getResultsObj().get(0);
        LoginUser.payeeUserName = payee.getLoginObj().getUsername();
        LoginUser.payeePassword = payee.getLoginObj().getPassword();

        log.info("End call to Random API to create Payee User");

    }

    @When("^I click on bill payment link$")
    public void i_click_on_bill_payment_link() throws Throwable {
        homePage.goToBillPayPage();
    }

    @And("^I enter the login details of Sender$")
    public void i_enter_the_login_details_of_Sender() throws Throwable {
        homePage.enterLoginDetails(LoginUser.senderUserName,LoginUser.senderPassword);
    }

    @And("^I click on Login IN button")
    public void i_click_on_login_button() throws Throwable {
        homePage.clickLoginInButton();
    }

    @And("^I see the user logged in application")
    public void i_see_the_user_logged_in_application() throws Throwable {
        homePage.clickLoginInButton();
    }

    @When("^I see the sender account details$")
    public void i_see_the_sender_account_details() throws Throwable {
        LoginUser.senderAccountNumber = billPaymentPage.getSenderAccountDetails();
    }

    @When("^I enter the bill payment details as (.+)$")
    public void i_enter_the_sender_details_as(String amount) throws Throwable {
        billPaymentPage.enterBillPaymentDetails(payee,amount);
    }

    @And("^I press submit button$")
    public void i_press_submit_button() throws Throwable {
        billPaymentPage.clickSubmitButton();
    }

    @Then("I verify the transaction details as (.+)$")
    public void i_verify_the_transaction_details(String amount) {
        homePage.verifyTransactionDetails(LoginUser.senderAccountNumber,payee.getNameObj().getFirst(),payee.getNameObj().getLast(),amount);
    }

}
