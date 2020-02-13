package util;

import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
public class WaitClass {

  WebDriver driver;

  public WaitClass(WebDriver driver) {
    this.driver = driver;
  }

  public void waitInSeconds(int seconds) {
    log.info("Wait for {} seconds", seconds);
    driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
  }
}
