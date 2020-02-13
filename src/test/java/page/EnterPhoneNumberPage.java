package page;

import io.appium.java_client.MobileDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Slf4j
public class EnterPhoneNumberPage extends AbstractPage {

  private By phoneNumberInput = By.id("com.reporty.reporty:id/etInsertPhoneNumber");
  private By termsAndConditionsLink = By.id("com.reporty.reporty:id/tv_terms_and_conditions");
  private By sendButton = By.id("com.reporty.reporty:id/btn_send_phone_number");
  private By isThisYourPhoneNumberPopUp = By.id("android:id/title_template");
  private By editButton = By.id("android:id/button2");
  private By yesButton = By.id("android:id/button1");

  public EnterPhoneNumberPage(MobileDriver<WebElement> driver) {
    super(driver);
  }

  public void writePhoneNumber(String phoneNumber) {
    log.info("Enter phone number: {}", phoneNumber);
    inputText(phoneNumberInput, phoneNumber);
  }

  public void clickOnTermsAndConditionsLink() {
    log.info("Click on 'Terms and Conditions' link");
    clickElement(termsAndConditionsLink);
  }

  public void clickOnSendButton() {
    log.info("Click on 'send phone' button");
    clickElement(sendButton);
  }

  public void clickOnEditButton() {
    log.info("Click on 'edit' button on 'Is This Your Phone Number?' pop-up");
    clickElement(editButton);
  }

  public void clickOnYesButton() {
    log.info("Click on 'yes' button on 'Is This Your Phone Number?' pop-up");
    clickElement(yesButton);
  }

  public void waitForIsThisYourPhoneNumberPopUp(){
    log.info("Wait for 'Is This Your Phone Number?' pop-up");
    waitForElement(isThisYourPhoneNumberPopUp);
  }
}
