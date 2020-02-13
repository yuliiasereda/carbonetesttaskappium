package factory;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import java.net.URL;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

@Slf4j
public class DriverFactory {

  public static MobileDriver<WebElement> getDriver(URL url,
      DesiredCapabilities desiredCapabilities) {
    String platformName = desiredCapabilities.getCapability(MobileCapabilityType.PLATFORM_NAME)
        .toString();

    switch (Platform.valueOf(platformName)) {
      case ANDROID: {
        log.debug("Platform ANDROID initializing");
        return new AndroidDriver<>(url, desiredCapabilities);
      }
      case IOS: {
        log.debug("Platform IOS initializing");
        return new IOSDriver<>(url, desiredCapabilities);
      }
      default: {
        log.error("Platform is not specified");
        throw new RuntimeException("Platform is not specified");
      }
    }
  }
}
