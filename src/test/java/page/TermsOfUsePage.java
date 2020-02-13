package page;

import io.appium.java_client.MobileDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Slf4j
public class TermsOfUsePage extends AbstractPage {

  private By termsOfUseTitle = By
      .xpath("//android.widget.TextView[@text='Terms Of Use & Privacy Policy']");
  private By backButton = By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']");

  public TermsOfUsePage(MobileDriver<WebElement> driver) {
    super(driver);
  }

  public void clickOnBackButton() {
    log.info("Click on 'back' button");
    clickElement(backButton);
  }

  public boolean isTermsOfUseTitlePresent() {
    log.info("Verify is 'Terms Of Use' page title displayed");
    return isElementPresent(termsOfUseTitle);
  }

  public void waitForTermsOfUseTitle() {
    log.info("Wait for 'Terms Of Use' page title");
    waitForElement(termsOfUseTitle);
  }
}
