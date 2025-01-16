package tests;

import Listener.TestListener;
import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import utilities.Config;
import utilities.Constants;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
@Listeners({TestListener.class})
public abstract class AbstractTest
{
    protected WebDriver driver;
    @BeforeSuite
    public void setUP()
    {
        Config.initialize();
    }
    @BeforeTest
    public  void setWebDriver(ITestContext ctx) throws  Exception
    {
        this.driver = Boolean.parseBoolean(Config.getKey(Constants.GRID_ENABLED)) ? getRemoteDriver():getLocalDriver();
        ctx.setAttribute("driver",this.driver);
    }

    private WebDriver getRemoteDriver() throws MalformedURLException {
        Capabilities capabilities = new ChromeOptions();
        if(Constants.FIREFOX.equalsIgnoreCase(Config.getKey(Constants.BROWSER)))
        {
            capabilities = new FirefoxOptions();
        }
        String urlFormat = Config.getKey(Constants.GRID_URL_FORMAT);
        String hubHost = Config.getKey(Constants.GRID_HUB_HOST);
        String url = String.format(urlFormat,hubHost);
        return new RemoteWebDriver(new URL(url),capabilities);
    }
    private WebDriver getLocalDriver(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("test-type");
        options.addArguments("disable-notifications");
        options.setExperimentalOption("prefs", java.util.Map.of(
                "autofill.profile_enabled", false // Disable autofill
        ));
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(options);
    }
    @AfterTest
    public void quitDriver()
    {
        this.driver.quit();
    }
//    @AfterMethod
//    public void sleep()
//    {
//        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
//    }
}
