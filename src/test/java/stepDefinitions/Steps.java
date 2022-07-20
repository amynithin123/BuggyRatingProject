package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LoginPage;
import pageObjects.HomePage;

public class Steps extends BaseClass {

	@Before
	public void setup() throws IOException {

		logger = logger.getLogger("BNZProjectVersion1");
		PropertyConfigurator.configure("log4j.properties");

		configProp = new Properties();
		FileInputStream configPropfile = new FileInputStream("config.properties");
		configProp.load(configPropfile);

		String br = configProp.getProperty("browser");

		if (br.equals("chrome")) {

			logger.info("***Launching Chrome Browser***");

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (br.equals("firefox")) {

			logger.info("***Launching Firefox Browser***");

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		}
	}

	// Register.feature step definitions
	@Given("User Launch browser")
	public void user_launch_browser() {

		logger.info("******user launching browser******");

		lp = new LoginPage(driver);

	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {

		logger.info("******user opens URL******");

		driver.get(url);
		driver.manage().window().maximize();

	}

	@Then("User can view the Bugging Rating login page")
	public void user_can_view_the_bugging_rating_login_page() {

		logger.info("******Validating the title of the page******");
		String expected_title = "Buggy Cars Rating";

		Assert.assertEquals(expected_title, lp.getPageTitle());

	}

	@When("User clicks on Register")
	public void user_clicks_on_register() {

		logger.info("******Clicking on Register ling to register******");
		lp.clickRegister();

	}

	@When("User enters Login")
	public void user_enters_login() {

		logger.info("******Entering the unique login code- username******");
		String loginCode = randomString();
		lp.enterLoginCodeToRegister(loginCode);
	}

	@When("User enters First Name as {string}")
	public void user_enters_first_name_as(String firstname) {

		logger.info("******Entering the first name******");
		lp.enterFirstNameToRegister(firstname);

	}

	@When("User enters Last Name as {string}")
	public void user_enters_last_name_as(String lastname) {

		logger.info("******Entering the last name******");

		lp.enterLastNameToRegister(lastname);

	}

	@When("User enters Password as {string} and Confirm Password as {string}")
	public void user_enters_password_as_and_confirm_password_as(String password, String confirmpwd)
			throws InterruptedException {

		logger.info("******Entering the password and confirm password******");
		lp.enterPasswordToRegister(password);
		lp.enterConfirmPasswordToRegister(confirmpwd);

	}

	@When("User clicks on Register to submit registration")
	public void user_clicks_on_register_to_submit_registration() {

		logger.info("******Clicking on Submit button to submit the registration******");
		lp.submitRegistration();

	}

	@Then("validate that the user registration is successful {string}")
	public void validate_that_the_user_registration_is_successful(String title) {
		logger.info("******Validating successful login******");

		if (driver.getPageSource().contains("Registration is successful")) {
			Assert.assertTrue("Registration is successful", true);

		} else {

			System.out.println("Registration failed");

		}
	}

	// Login.feature step definitions

	@When("User enters Login code as {string}")
	public void user_enters_login_code_as(String code) {

		lp = new LoginPage(driver);

		logger.info("******Entering the login code-user name from the existing list of registered user******");
		lp.enterLoginCodeToLogin(code);

	}

	@When("User enters Password as {string} to login")
	public void user_enters_password_as_to_login(String password) {

		logger.info("******Entering the password to login to the application******");

		lp.enterPasswordToLogin(password);

	}

	@When("User clicks on Login to login to application")
	public void user_clicks_on_login_to_login_to_application() {

		logger.info("******Clicking on login button******");
		lp.clickLogin();

	}

	@Then("validate that {string} can successfully login to the application")
	public void validate_that_can_successfully_login_to_the_application(String firstname) {

		hp = new HomePage(driver);

		logger.info("******Validating the successful login to the application by matching profile name******");
		hp.validateUserLogin(firstname);
	}

	@Then("User log out of the application")
	public void user_log_out_of_the_application() {

		logger.info("******Clicking on logout button******");
		hp.clickLogout();
	}

	@Then("validate that invalid username or password {string} is displayed")
	public void validate_that_invalid_username_or_password_is_displayed(String expectedmsg) {

		String actualError = lp.validateInvalidUserNamePasswordErrorMsg();

		Assert.assertEquals(expectedmsg, actualError);
	}

	@Then("close the browser")
	public void close_the_browser() {

		logger.info("******Closing all the browsers******");
		driver.quit();
	}

	// Ratings.feature step definitions

	@When("User clicks on Buggy Rating")
	public void user_clicks_on_buggy_rating() {

		logger.info("******Clicking on Buggy Rating logo******");
		hp = new HomePage(driver);
		hp.clickBuggyRatingIcon();

	}

	@When("User clicks on Overall Rating")
	public void user_clicks_on_overall_rating() {

		logger.info("******Clicking on over all Rating to view the list of registered cars******");
		hp.clickOverAllRating();

	}

	@Then("validate the ranking of the cars")
	public void validate_the_ranking_of_the_cars() {

		logger.info("******Validating the ranking of the cars******");
		hp.validateRankingOfAllRegisteredModels();
	}

	// Votes.feature step definitions

	@When("User enters LoginCode {string}")
	public void user_enters_login_code(String code) {

		lp = new LoginPage(driver);

		logger.info("******Entering the login code to register******");
		lp.enterLoginCodeToRegister(code);

	}

	@When("User clicks on Popular Model")
	public void user_clicks_on_popular_model() {

		hp = new HomePage(driver);

		logger.info("******Clicking on the popular model******");
		hp.clickPopuplarModel();

	}

	@Then("User enters the comment {string} and clicks on Vote to validate the comment has been added")
	public void user_enters_the_comment_and_clicks_on_vote_to_validate_the_comment_has_been_added(String cmt)
			throws InterruptedException {

		logger.info("******Adding a vote for the car with comment******");
		hp.addVoteForPopularModel(cmt);
	}

//Register.feature step definitions

	@When("User enters Password as {string} and mismtach Password as {string}")
	public void user_enters_password_as_and_mismtach_password_as(String password, String missmatchpwd)
			throws InterruptedException {

		lp = new LoginPage(driver);

		logger.info("******Entering password and a mis match password to validate the exception******");
		lp.enterPasswordToRegister(password);
		lp.entermismatchConfirmPasswordToRegister(missmatchpwd);

	}

	@Then("Add unique login credentials to register and login {string} {string} {string} to vote")
	public void add_unique_login_credentials_to_register_and_login_to_vote(String firtsname, String lastname,
			String pwd) throws InterruptedException {

		logger.info("******Registering with unique login code and logging in to the application******");
		String loginCode = randomString();
		lp.enterLoginCodeToRegister(loginCode);
		lp.enterFirstNameToRegister(firtsname);
		lp.enterLastNameToRegister(lastname);
		lp.enterPasswordToRegister(pwd);
		lp.enterConfirmPasswordToRegister(pwd);
		lp.submitRegistration();
		lp.enterLoginCodeToLogin(loginCode);
		lp.enterPasswordToLogin(pwd);
		lp.clickLogin();
	}

	@Then("Validate the {string} exception message is displayed")
	public void validate_the_exception_message_is_displayed(String expectedAlert) throws InterruptedException {

		String alertsInForm = lp.getExceptionAlertMsgForReg();

		logger.info("******Validating the exception for unsuccessful registration******");
		if (alertsInForm.contains(expectedAlert)) {

			Assert.assertTrue(expectedAlert, true);
		} else {

			Assert.fail();
		}

	}

	@When("User enters LoginCode {string} with more than {int} characters")
	public void user_enters_login_code_with_more_than_characters(String loginCode, Integer no) {

		lp = new LoginPage(driver);

		String max_login = lp.randomString(loginCode, 60);

		logger.info("******Entering a login code over the maximum limit of 50******");

		lp.enterLoginCodeToRegister(max_login);

	}

}