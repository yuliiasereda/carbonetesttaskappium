package test;

import page.EnterPhoneNumberPage;
import page.TermsOfUsePage;
import page.VerifyCodePage;
import util.WaitClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {

  private EnterPhoneNumberPage enterPhoneNumberPage;
  private VerifyCodePage verifyCodePage;
  private TermsOfUsePage termsOfUsePage;
  private WaitClass waitClass;

  @BeforeClass
  public void initPages() {
    enterPhoneNumberPage = new EnterPhoneNumberPage(driver);
    verifyCodePage = new VerifyCodePage(driver);
    termsOfUsePage = new TermsOfUsePage(driver);
    waitClass = new WaitClass(driver);
  }

  @Test
  public void enterPhoneNumberAndNonValidCode() {
    //Enter phone number: 972 0526995055
    enterPhoneNumberPage.writePhoneNumber("972 0526995055");

    //Click on “terms and conditions”
    enterPhoneNumberPage.clickOnTermsAndConditionsLink();

    //Assert page “terms and conditions” appears
    termsOfUsePage.waitForTermsOfUseTitle();
    Assert.assertTrue(termsOfUsePage.isTermsOfUseTitlePresent());
//    termsOfUsePage.clickOnBackButton(); wasn't specified in the test cases, but it is necessary for the correct flow

    // Click on “Send” button
    enterPhoneNumberPage.clickOnSendButton();

    //On “Is this your phone number?” msg click on “Edit”
    enterPhoneNumberPage.waitForIsThisYourPhoneNumberPopUp();
    enterPhoneNumberPage.clickOnEditButton();

    //Click on “Send” button again
    enterPhoneNumberPage.clickOnSendButton();

    //and then on “Yes”
    enterPhoneNumberPage.waitForIsThisYourPhoneNumberPopUp();
    enterPhoneNumberPage.clickOnYesButton();

    //On SMS verification page , wait for 20 sec
    verifyCodePage.isPleaseInsertTheCodeThatWasSentToYouBySmsIsPresent();
    waitClass.waitInSeconds(20);

    //and then click on “Resend”
    verifyCodePage.clickOnResendButton();

//    enterPhoneNumberPage.clickOnSendButton(); was not specified in the test cases, but it is necessary for the correct flow

//    enterPhoneNumberPage.waitForIsThisYourPhoneNumberPopUp(); was not specified in the test cases, but it is necessary for the correct flow
//    enterPhoneNumberPage.clickOnYesButton(); was not specified in the test cases, but it is necessary for the correct flow

    // Enter “4444”
    verifyCodePage.enterCode("4444");

    //Assert “ The code does not match. Please try again”
    Assert.assertEquals(verifyCodePage.getTextOfErrorMessage(),
        "The code does not match. Please try again");
  }
}
