package page;

import io.appium.java_client.AppiumFluentWait;
import io.appium.java_client.MobileDriver;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

@Slf4j
public abstract class AbstractPage {

  protected MobileDriver<WebElement> driver;
  protected FluentWait<MobileDriver<WebElement>> wait;

  public AbstractPage(MobileDriver<WebElement> driver) {
    this.driver = driver;
    wait = new AppiumFluentWait<>(driver)
        .withTimeout(5, TimeUnit.SECONDS)
        .pollingEvery(250, TimeUnit.MILLISECONDS)
        .ignoring(NoSuchElementException.class)
        .ignoring(TimeoutException.class);
    ;
  }

  public void clickElement(By elementPath) {
    wait.until(ExpectedConditions.elementToBeClickable(elementPath));
    driver.findElement(elementPath).click();
  }

  public void inputText(By elementPath, String text) {
    wait.until(ExpectedConditions.presenceOfElementLocated(elementPath));
    driver.findElement(elementPath).sendKeys(text);
  }

  public String getText(By elementPath) {
    wait.until(ExpectedConditions.presenceOfElementLocated(elementPath));
    return driver.findElement(elementPath).getText();
  }

  public void waitForElement(By elementPath) {
    wait.until(ExpectedConditions.presenceOfElementLocated(elementPath));
  }

  public boolean isElementPresent(By elementPath) {
    try {
      driver.findElement(elementPath);
      return true;
    } catch (NoSuchElementException e) {
      log.warn("Element wasn't found");
      return false;
    }
  }
}
