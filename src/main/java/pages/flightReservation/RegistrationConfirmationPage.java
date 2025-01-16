package pages.flightReservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.AbstractPage;

public class RegistrationConfirmationPage extends AbstractPage
{
    @FindBy(id="go-to-flights-search")
    private WebElement flightSearchBtn;
    public RegistrationConfirmationPage(WebDriver driver)
    {
        super(driver);
    }
    public void clickFlightSearchBtn()
    {
        this.flightSearchBtn.click();
    }

    @Override
    public boolean isAt()
    {
        this.webDriverWait.until(ExpectedConditions.visibilityOf(this.flightSearchBtn));
        return this.flightSearchBtn.isDisplayed();
    }
}
