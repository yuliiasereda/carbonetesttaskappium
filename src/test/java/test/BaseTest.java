package test;

import factory.DriverFactory;
import lombok.extern.slf4j.Slf4j;
import util.PropertiesHandler;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

@Slf4j
public class BaseTest {

  private static final String PATH_TO_CREDENTIALS_FILE = "src/test/resources/config.properties";
  protected MobileDriver<WebElement> driver;

  @BeforeSuite
  public void setUp() throws MalformedURLException {
    log.debug("Properties initializing");
    PropertiesHandler propertiesHandler = new PropertiesHandler(PATH_TO_CREDENTIALS_FILE);
    String filePath = propertiesHandler.getProperty("appPath");
    String url = propertiesHandler.getProperty("url");
    File androidApp = new File(filePath);

    log.debug("Desired Capabilities initializing");
    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability(MobileCapabilityType.DEVICE_NAME,
        propertiesHandler.getProperty("deviceName"));
    caps.setCapability(MobileCapabilityType.PLATFORM_NAME,
        propertiesHandler.getProperty("platformName"));
    caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,
        propertiesHandler.getProperty("platformVersion"));
    caps.setCapability(MobileCapabilityType.APP, androidApp);

    log.debug("Driver initializing");
    driver = DriverFactory.getDriver(new URL(url), caps);
  }

  @AfterSuite
  public void tearDown() {
    log.debug("Driver quit");
    driver.quit();
  }
}
