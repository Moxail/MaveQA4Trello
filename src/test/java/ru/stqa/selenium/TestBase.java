package ru.stqa.selenium;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Capabilities;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterMethod;

import ru.stqa.selenium.factory.WebDriverPool;
import ru.stqa.selenium.pages.HomePageHelper;

/**
 * Base class for TestNG-based test classes
 */
public class TestBase {

  protected static URL gridHubUrl = null;
  protected static String baseUrl;
  protected static Capabilities capabilities;
  public static final String LOGIN = "marinaqatest2019@gmail.com";
  public static final String PASSWORD = "marinaqa";
  HomePageHelper homePage;

  protected WebDriver driver;

  @BeforeSuite
  public void initTestSuite() throws IOException {
    SuiteConfiguration config = new SuiteConfiguration();
    baseUrl = config.getProperty("site.url");
    if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
      gridHubUrl = new URL(config.getProperty("grid.url"));
    }
    capabilities = config.getCapabilities();
  }

  @BeforeMethod
  public void initWebDriver() {
    /*ChromeOptions options = new ChromeOptions();
    options.addArguments("--lang=" + "rus");
    driver = new ChromeDriver(options);*/
    driver = WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities);
    homePage = PageFactory.initElements(driver,HomePageHelper.class);
    //===========Enter to Trello====
    driver.get(baseUrl);
    driver.manage().window().fullscreen();
    homePage.waitUntilPageIsLoaded();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    WebDriverPool.DEFAULT.dismissAll();
    //driver.quit();
  }
}
