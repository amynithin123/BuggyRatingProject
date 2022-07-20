package pageObjects;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.WaitHelper;

public class LoginPage {

	public WebDriver ldriver;
	WaitHelper waithelper;
	WebDriverWait wait;

	public LoginPage(WebDriver rdriver) {

		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
		waithelper = new WaitHelper(ldriver);
		wait = new WebDriverWait(ldriver, 20);

	}

	By loginField = By.cssSelector("input[name='login']");
	By passwordField = By.cssSelector("input[name='password']");
	By loginButton = By.cssSelector("button[type='submit']");
	By registerLink = By.xpath("//a[@href='/register']");
	By regLoginField = By.id("username");
	By regFirstNameField = By.id("firstName");
	By regLastNameField = By.id("lastName");
	By regPasswordField = By.id("password");
	By confirmPasswordField = By.id("confirmPassword");
	By registerSubmitButton = By.xpath("//button[@class='btn btn-default']");
	By registersuccessmsg = By.xpath("//div[contains(text(),'Registration is successful')]");

	By userexistsalert = By.cssSelector("div[class='result alert alert-danger']");
	By regForm = By.xpath("//main[@class='row']/my-register/div/div/form");
	By invalidUsernamePwdmsg = By.cssSelector("span[class='label label-warning']");

	public String getPageTitle() {

		return ldriver.getTitle();
	}

	public void clickRegister() {

		ldriver.findElement(registerLink).click();
	}

	public void enterLoginCodeToRegister(String logincode) {
		ldriver.findElement(regLoginField).clear();

		ldriver.findElement(regLoginField).sendKeys(logincode);

	}

	public String randomString(String chars, int length) {
		Random rand = new Random();
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < length; i++) {
			buf.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return buf.toString();
	}

	public void enterFirstNameToRegister(String firstname) {

		ldriver.findElement(regFirstNameField).clear();
		ldriver.findElement(regFirstNameField).sendKeys(firstname);
	}

	public void enterLastNameToRegister(String lastname) {

		ldriver.findElement(regLastNameField).clear();
		ldriver.findElement(regLastNameField).sendKeys(lastname);
	}

	public void enterPasswordToRegister(String password) {

		ldriver.findElement(regPasswordField).clear();
		ldriver.findElement(regPasswordField).sendKeys(password);
	}

	public void enterConfirmPasswordToRegister(String password) {

		ldriver.findElement(confirmPasswordField).clear();
		ldriver.findElement(confirmPasswordField).sendKeys(password);

	}

	public void entermismatchConfirmPasswordToRegister(String mismatch_pwd) {

		ldriver.findElement(confirmPasswordField).clear();
		ldriver.findElement(confirmPasswordField).sendKeys(mismatch_pwd);

	}

	public void submitRegistration() {

		ldriver.findElement(registerSubmitButton).click();

	}

	public String getSuccessRegistrationmsg() {

		String successmessage = ldriver.findElement(registersuccessmsg).getText();

		return successmessage;

	}

	public void enterLoginCodeToLogin(String code) {

		ldriver.findElement(loginField).clear();

		ldriver.findElement(loginField).sendKeys(code);

	}

	public void enterPasswordToLogin(String pwd) {

		ldriver.findElement(passwordField).clear();
		ldriver.findElement(passwordField).sendKeys(pwd);

	}

	public void clickLogin() {

		ldriver.findElement(loginButton).click();

	}

	public boolean validateUserExistsExceptionForRegistration() {

		boolean flag = false;
		String regAlert = ldriver.findElement(userexistsalert).getText();

		String user_exists_exception = "UsernameExistsException: User already exists";

		if (regAlert.equals(user_exists_exception)) {

			flag = true;
		}
		return flag;

	}

	public String getUserExistsExceptionMsg() {

		wait.until(ExpectedConditions.visibilityOfElementLocated(userexistsalert)).isDisplayed();
		String regAlert = ldriver.findElement(userexistsalert).getText();
		return regAlert;

	}

	public String getExceptionAlertMsgForReg() throws InterruptedException {

		Thread.sleep(1000);
		String alertmsg = ldriver.findElement(regForm).getText().trim();

		return alertmsg;

	}

	public void clearFirstNameField() {

		ldriver.findElement(regFirstNameField).clear();
	}

	public void clearPasswordField() {

		ldriver.findElement(passwordField).clear();
	}

	public String validateInvalidUserNamePasswordErrorMsg() {

		wait.until(ExpectedConditions.visibilityOfElementLocated(invalidUsernamePwdmsg)).isDisplayed();

		String actual_error_msg = ldriver.findElement(invalidUsernamePwdmsg).getText();
		return actual_error_msg;

	}
}
