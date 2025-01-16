package pages.vendorPortal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.AbstractPage;
import pages.flightReservation.FlightConfirmationPage;

public class DashBoardPage extends AbstractPage
{
    private static final Logger log = LoggerFactory.getLogger(DashBoardPage.class);
    @FindBy(id="monthly-earning")
    private WebElement monthlyEarning;
    @FindBy(id="annual-earning")
    private WebElement annualEarning;
    @FindBy(id="profit-margin")
    private WebElement profitMargin;
    @FindBy(id="available-inventory")
    private WebElement availableInventory;
    @FindBy(xpath = "//input[@type='search']")
    private WebElement searchBox;
    @FindBy(id="dataTable_info")
    private WebElement numofResults;
    @FindBy(xpath = "//*[@id='userDropdown']/img")
    private WebElement userProfile;
    @FindBy(linkText = "Logout")
    private WebElement logoutBtn;
    @FindBy(xpath = "//*[@id='logoutModal']/div/div/div[3]/a")
    private WebElement closeBtn;
    public DashBoardPage(WebDriver driver)
    {
        super(driver);
    }

    @Override
    public boolean isAt()
    {
        this.webDriverWait.until(ExpectedConditions.visibilityOf(this.monthlyEarning));
        return this.monthlyEarning.isDisplayed();
    }
    public String getMonthlyEarning()
    {
        return this.monthlyEarning.getText();
    }
    public String getAnnualEarning()
    {
        return this.annualEarning.getText();
    }
    public String getProfitMargin()
    {
        return this.profitMargin.getText();
    }
    public String getAvailInv()
    {
        return this.availableInventory.getText();
    }
    public void setSearchBox(String text)
    {
        this.searchBox.sendKeys(text);
    }
    public int getCount()
    {
        int result = Integer.parseInt(this.numofResults.getText().split(" ")[5]);
        log.info("Result : {}",result);
        return result;
    }
    public void logout()
    {
        this.userProfile.click();
        this.webDriverWait.until(ExpectedConditions.visibilityOf(this.logoutBtn));
        this.logoutBtn.click();
        this.webDriverWait.until(ExpectedConditions.visibilityOf(this.closeBtn));
        this.closeBtn.click();
    }
}
