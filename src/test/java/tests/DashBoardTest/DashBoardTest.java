package tests.DashBoardTest;

import ch.qos.logback.core.encoder.JsonEscapeUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.AbstractPage;
import pages.vendorPortal.DashBoardPage;
import pages.vendorPortal.LoginPage;
import tests.AbstractTest;
import tests.DashBoardTest.model.TestData;
import utilities.Config;
import utilities.Constants;
import utilities.jsonUtil;

public class DashBoardTest extends AbstractTest
{
    private static final Logger log = LoggerFactory.getLogger(DashBoardTest.class);
    private LoginPage loginPage;
    private DashBoardPage dashBoardPage;
    private TestData testData;
    @BeforeTest
    @Parameters("testDataPath")
    public  void setPageObject(String testDataPath) throws  Exception
    {
        this.loginPage = new LoginPage(driver);
        this.dashBoardPage = new DashBoardPage(driver);
        this.testData = jsonUtil.getResource(testDataPath, TestData.class);
    }
    @Test
    public void loginTest()
    {
        loginPage.goTo(Config.getKey(Constants.VENDOR_PORTAL_URL));
        loginPage.isAt();
        loginPage.login(testData.name(), testData.password());
    }
    @Test(dependsOnMethods = "loginTest")
    public void DashBoardTestSam()
    {
        dashBoardPage.isAt();
        Assert.assertEquals(dashBoardPage.getMonthlyEarning(),testData.monthlyEarning());
        Assert.assertEquals(dashBoardPage.getAnnualEarning(),testData.annualEarning());
        Assert.assertEquals(dashBoardPage.getProfitMargin(),testData.profitMargin());
        Assert.assertEquals(dashBoardPage.getAvailInv(),testData.availableInventory());
        dashBoardPage.setSearchBox(testData.searchKeyword());
        Assert.assertEquals(dashBoardPage.getCount(),testData.resultCount());
    }
    @Test(dependsOnMethods = "DashBoardTestSam")
    public void logoutTest()
    {
        dashBoardPage.logout();
        loginPage.isAt();
    }
}
