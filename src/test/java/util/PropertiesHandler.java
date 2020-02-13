package util;

import exception.ConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PropertiesHandler {

  private Properties properties;

  public PropertiesHandler(String filePath) {
    properties = new Properties();
    try (FileInputStream inputStream = new FileInputStream(filePath)) {
      properties.load(inputStream);
    } catch (IOException e) {
      log.error("Couldn't find property file");
      throw new ConfigurationException("Couldn't find a file on this path: " + filePath);
    }
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }
}
