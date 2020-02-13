package page;

import io.appium.java_client.MobileDriver;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Slf4j
public class VerifyCodePage extends AbstractPage {

  public static final String CODE_SEPARATOR = "";

  private By isPleaseInsertTheCodeThatWasSentToYouBySmsText = By
      .id("com.reporty.reporty:id/tv_note");
  private By resendButton = By.id("com.reporty.reporty:id/btn_resend_code");
  private By errorMessage = By.id("com.reporty.reporty:id/tv_code_status");
  private String codeInputId = "com.reporty.reporty:id/input_cell_%s";

  public VerifyCodePage(MobileDriver<WebElement> driver) {
    super(driver);
  }

  public void isPleaseInsertTheCodeThatWasSentToYouBySmsIsPresent() {
    log.info("Verify is 'Please Insert The Code That Was Sent To You By Sms' error message displayed");
    isElementPresent(isPleaseInsertTheCodeThatWasSentToYouBySmsText);
  }

  public void clickOnResendButton() {
    log.info("Click on 'resend' button");
    clickElement(resendButton);
  }

  public String getTextOfErrorMessage() {
    String text = getText(errorMessage);
    log.info("Read text '{}' from error message", text);
    return text;
  }

  public void enterCode(String code) {
    if (code.length() > 4) {
      log.warn("Code data shouldn't be greater than 4 symbols");
      throw new AssertionError("Code data shouldn't be greater than 4 symbols");
    }
    AtomicInteger index = new AtomicInteger(0);
    Arrays.stream(code.split(CODE_SEPARATOR))
        .forEach((c) -> {
          log.info("Insert data into '{}' input cell", index);
          inputText(By.id(String.format(codeInputId, index.incrementAndGet())), c);
        });

  }
}
