package pageObjects;

import java.util.ArrayList;
import java.util.Collections;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.WaitHelper;

public class HomePage {

	public WebDriver ldriver;
	WaitHelper waithelper;
	WebDriverWait wait;

	public HomePage(WebDriver rdriver) {

		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
		waithelper = new WaitHelper(ldriver);
		wait = new WebDriverWait(ldriver, 20);
	}

	By profileLink = By.cssSelector("a[href='/profile']");
	By profileNameLink = By.cssSelector("span[class='nav-link disabled']");
	By logOutlink = By.xpath("//a[text()='Logout']");
	By buggyRatingIcon = By.xpath("//a[text()='Buggy Rating']");
	By popularMake = By.cssSelector("img[title='Alfa Romeo']");
	By popularModel = By.cssSelector("img[title='Guilia Quadrifoglio']");
	By overallRatingModels = By.cssSelector("img[src='/img/overall.jpg']");
	By pages = By.xpath("//div[@class='row']//div[@class='pull-xs-right']");
	By nextIcon = By.xpath("//a[normalize-space()='»']");
	By commentTextBox = By.id("comment");
	By voteButton = By.cssSelector("button[class='btn btn-success']");
	By alreadyVotedText = By.xpath("//p[text()='Thank you for your vote!']");
	By addedCommentSection = By.xpath("//tbody/tr[1]/td[3]");

	public String getPageTitle() {

		return ldriver.getTitle();
	}

	public boolean validateUserLogin(String firstname) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(profileNameLink)).isDisplayed();

		boolean flag = false;
		String actual_profile_name_link = ldriver.findElement(profileNameLink).getText();

		if (actual_profile_name_link.contains(firstname)) {

			flag = true;
		}
		return flag;

	}

	public void clickLogout() {

		ldriver.findElement(logOutlink).click();
	}

	public void clickBuggyRatingIcon() {

		ldriver.findElement(buggyRatingIcon).click();
	}

	public void clickOnPopularMake() {

		ldriver.findElement(popularMake).click();

	}

	public void clickOverAllRating() {

		ldriver.findElement(overallRatingModels).click();

	}

	public boolean validateRankingOfAllRegisteredModels() {

		boolean flag = false;

		wait.until(ExpectedConditions.visibilityOfElementLocated(pages)).isDisplayed();
		String text = ldriver.findElement(pages).getText();

		int index = text.indexOf("f");
		int current_page_start_index = text.indexOf("e");
		int currentpage_end_index = text.indexOf("o");

		String page = text.substring(current_page_start_index + 1, currentpage_end_index).trim();

		int currentpage = Integer.valueOf(page);

		String total_pages = text.substring(index + 1).trim();

		if (!total_pages.equals(currentpage)) {

			int rows = ldriver.findElements(By.xpath("//tbody/tr")).size();

			String firstpart = "//tbody/tr[";
			String enginesecondpart = "]/td[6]";
			String votesecondpart = "]/td[5]";
			String ranksecondpart = "]/td[4]";
			String modelsecondpart = "]/td[3]";
			String makesecondpart = "]/td[2]";

			ArrayList<Integer> array = new ArrayList<Integer>();

			for (int i = 1; i <= rows; i++) {

				String rank = firstpart + i + ranksecondpart;

				String ranking = ldriver.findElement(By.xpath(rank)).getText();

				Double dranking = Double.parseDouble(ranking);
				int intrank = dranking.intValue();

				if (intrank == 1) {

					String votes = firstpart + i + votesecondpart;
					String totalvotes = ldriver.findElement(By.xpath(votes)).getText();
					String carmake = firstpart + i + makesecondpart;
					String carmodel = firstpart + i + modelsecondpart;
					String carengine = firstpart + i + enginesecondpart;

					String make = ldriver.findElement(By.xpath(carmake)).getText();
					String model = ldriver.findElement(By.xpath(carmodel)).getText();
					String engine = ldriver.findElement(By.xpath(carengine)).getText();

					System.out.println("The total no of votes for the highest ranking car is:" + totalvotes);
					System.out.println("The make of the top ranked car is:" + make);
					System.out.println("The model of the top ranked car is:" + model);
					System.out.println("The engine of the top ranked car is:" + engine);

					String vote = ldriver.findElement(By.xpath(votes)).getText();
					Double dvote = Double.parseDouble(vote);
					int intvote = dvote.intValue();
					array.add(intvote);

				}
				ldriver.findElement(nextIcon).click();

			}
			Collections.sort(array);
			int totalVotesExpected = array.get(0);
			int highest_votes = array.get(array.size() - 1);

			if (totalVotesExpected == highest_votes) {

				flag = true;
			}

		}
		return flag;

	}

	public void clickPopuplarModel() {

		wait.until(ExpectedConditions.visibilityOfElementLocated(popularModel)).isDisplayed();

		ldriver.findElement(popularModel).click();
	}

	public void addVoteForPopularModel(String comment) throws InterruptedException {

		if (wait.until(ExpectedConditions.visibilityOfElementLocated(commentTextBox)).isDisplayed()) {

			ldriver.findElement(commentTextBox).sendKeys(comment);
			ldriver.findElement(voteButton).click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(addedCommentSection)).isDisplayed();

			if (ldriver.findElement(addedCommentSection).getText().equals(comment)) {

				System.out.println("The comment has been successfully added for the vote!");
			} else {

				System.out.println("The comment failed to add for the vote!!!");
			}

		} else if (ldriver.findElement(alreadyVotedText).isDisplayed()) {

			System.out.println(
					"The user has already voted for the model. Please try with a different login to add the vote!");
		}

	}

}
